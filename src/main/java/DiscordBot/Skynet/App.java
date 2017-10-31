package DiscordBot.Skynet;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import music.MusicManager;
import music.MusicPlayer;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.entities.impl.MessageEmbedImpl;
import net.dv8tion.jda.core.events.message.MessageEmbedEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import pokemon.BattleModel;

public class App extends ListenerAdapter
{
	private static final char[][] CommandArray = null;
	final MusicManager manager = new MusicManager();
	BattleModel battle;
    public static void main( String[] args ) throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException
    {
        JDA bot = new JDABuilder(AccountType.BOT).setToken(inputToken()).buildBlocking(); //SET TOKEN
        bot.addEventListener(new App());
    }
    
    @Override 
    public void onMessageReceived(MessageReceivedEvent e)
    {
        Message objMsg = e.getMessage();
        MessageChannel objChannel = e.getChannel();
        User objUser = e.getAuthor();   
        TextChannel objTextChannel = e.getTextChannel();
        Guild objGuild = e.getGuild();
        String commandarray[];
        try
        {
        	commandarray = objMsg.getContent().split(" ", 2);
            if (objMsg.getContent().substring(0,2).equals("//"))
            {
            	callCommand(commandarray, objMsg, objChannel, objUser, objTextChannel, objGuild);
            }
        }
        catch(StringIndexOutOfBoundsException exception)
        {}
    }
    private void quote(MessageChannel objChannel) 
    {
    	String path = "quoteFile.txt";
    	try
    	{
    		BufferedReader textreader = new BufferedReader(new FileReader(path));
    		int numberoflines = 0;
    		while(textreader.readLine() != null)
    		{
    			numberoflines ++;
    		}
    		textreader.close();
    		String[] quotearray = new String[numberoflines]; 
    		BufferedReader textreaderforquotes = new BufferedReader(new FileReader(path));
    		for(int i = 0; i < numberoflines; i++)
    		{
    			quotearray[i] = textreaderforquotes.readLine();
    		}
    		objChannel.sendMessage(quotearray[(int)(Math.random()*numberoflines)]).queue();
    		textreaderforquotes.close();
    		
    	}  	
    	catch(IOException e) 
    	{
    		objChannel.sendMessage("Somethng went wrong, missing quotefile string, check App.java").queue();
    	} 	
    }
    
    private void quoteMe(String quote, MessageChannel objChannel)
    {
    	//todo
    }
    
    private void playSong(Guild guild, TextChannel textchannel, User user, String command)
    {
		if(guild == null)
		{
			return;
		}
		if(!guild.getAudioManager().isConnected() && !guild.getAudioManager().isAttemptingToConnect())
		{
			VoiceChannel voicechannel = guild.getMember(user).getVoiceState().getChannel();
			if(voicechannel == null)
			{
				textchannel.sendMessage("You must join a channel first").queue();
			}
			guild.getAudioManager().openAudioConnection(voicechannel);
		}
		manager.loadTrack(textchannel, command);
    }
    private void skipSong(Guild guild, TextChannel textchannel)
    {
    	if(!guild.getAudioManager().isConnected() && !guild.getAudioManager().isAttemptingToConnect())
    	{
    		textchannel.sendMessage("Player is not currently playing song.").queue();
    		return;
    	}
    	manager.getPlayer(guild).skipTrack();
    	textchannel.sendMessage("Skipping current song").queue();
    }
    private void clearSongs(TextChannel textchannel)
    {
    	MusicPlayer player = manager.getPlayer(textchannel.getGuild());
    	if(player.getListener().getTracks().isEmpty())
    	{
    		textchannel.sendMessage("Your playlist is already empty.").queue();
    		return;
    	}
    	player.getListener().getTracks().clear();
    	textchannel.sendMessage("Playlist cleared.").queue();
    	
    	
    }
    private void songPlaying(Guild guild, TextChannel textchannel) //fix
    {
    	if(!guild.getAudioManager().isConnected() && !guild.getAudioManager().isAttemptingToConnect())
    	{
    		textchannel.sendMessage("Player is not currently playing song.").queue();
    		return;
    	}
    	textchannel.sendMessage("Currently playing " + manager.getPlayer(guild).getAudioPlayer().getPlayingTrack().toString()).queue();
    }
    
    private void flipCoin(String calledside, MessageChannel objChannel, User objUser)
    {
    	if (calledside.equals("heads") || calledside.equals("tails"))
    	{
    		int Random = (int)(Math.random()*2);
        	if (Random == 1 && calledside.equals("heads") || Random == 0 && calledside.equals("tails"))
        	{
        		objChannel.sendMessage(objUser.getName() + " called the side CORRECTLY").queue();
        		return;
        	}
        	objChannel.sendMessage(objUser.getName() + " called the side INCORRECTLY").queue();
        	return;
    	}
		objChannel.sendMessage(calledside + " is not heads or tails bud, try again.").queue();
    	
    }
    private void rollDice(MessageChannel objChannel)
    {
    	objChannel.sendMessage("You rolled a " + (int)(Math.random()*6)).queue();
    }
    
    private void joke(MessageChannel objChannel)
    {
    	objChannel.sendMessage("%joke").queue();
    	//make this not piggyback off areks bot eventually
    }
    private void pokemonBattle(Guild guild, TextChannel textchannel, User user, String command)
    {
    	if(command.equals("NewBattle") || battle.checkIfBattleWon())
    		battle = new BattleModel("player1", "player2");
    	textchannel.sendMessage(battle.toEmbded()).queue();
    	textchannel.sendMessage(battle.currentPokemonStringBuilder()).queue();
    	//playSong(guild, textchannel, user, "https://www.youtube.com/watch?v=8Cw3vfuHh_A");
    }
    
    private static String inputToken()
    {
    	System.out.println("Enter your bot token: ");
    	Scanner scanner = new Scanner(System.in);
    	return scanner.nextLine();
    }
    
    private void callCommand(String[] commandarray, Message objMsg, MessageChannel objChannel, User objUser, TextChannel objTextChannel, Guild objGuild)
    {    	
    	if (commandarray[0].equalsIgnoreCase("//quote"))
    	{
    		quote(objChannel);
    	}
    	else if(commandarray[0].equalsIgnoreCase("//quoteme"))
    	{
    		try
    		{
    			quoteMe(commandarray[1], objChannel);
    		}
    		catch(Exception exception) //FIX EXCEPTION CONDTION
    		{
    			objChannel.sendMessage("No quote specified, try again").queue();
    		}
    	}
    	else if(commandarray[0].equalsIgnoreCase("//play"))
    	{
    		try
    		{
    			playSong(objGuild, objTextChannel, objUser, commandarray[1]);
    		}
    		catch(Exception exception) //FIX EXCEPTION CONDTION
    		{
    			objChannel.sendMessage("No song specified, try again").queue();
    		}
    	}
    	else if(commandarray[0].equalsIgnoreCase("//flipcoin"))
    	{
    		try
    		{
    			flipCoin(commandarray[1], objChannel, objUser);
    		}
    		catch(Exception exception) //FIX EXCEPTION CONDTION
    		{
    			objChannel.sendMessage("Please call heads or tails before flipping.").queue();
    		}
    	}
    	else if(commandarray[0].equalsIgnoreCase("//skip"))
    	{
    		skipSong(objGuild, objTextChannel);
    	}
    	else if(commandarray[0].equalsIgnoreCase("//clear"))
    	{
    		clearSongs(objTextChannel);
    	}
    	else if(commandarray[0].equalsIgnoreCase("//playing"))
    	{
    		songPlaying(objGuild, objTextChannel);
    	}
    	else if(commandarray[0].equalsIgnoreCase("//dice"))
    	{
    		rollDice(objChannel);
    	}
    	else if(commandarray[0].equalsIgnoreCase("//joke"))
    	{
    		joke(objChannel);
    	}
    	else if(commandarray[0].equalsIgnoreCase("//pokemonbattle"))
    	{
    		try
    		{
    			pokemonBattle(objGuild, objTextChannel, objUser, commandarray[1]);
    		}
    		catch(Exception e)
    		{
    			objChannel.sendMessage(e.getMessage()).queue();
    			objChannel.sendMessage(e.getCause().getMessage()).queue();
    		}
    	}
    }
    
}


