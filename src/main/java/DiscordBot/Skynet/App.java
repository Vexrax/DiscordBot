package DiscordBot.Skynet;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.Channel;
import java.util.HashMap;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import org.omg.CORBA.SystemException;

import Backend.BotListener;
import Backend.CommandParser;
import Commands.Command;
import Commands.FlipCoinCommand;
import Commands.PingCommand;
import Commands.PlaySongCommand;
import Commands.PokemonBattleCommand;
import Commands.QuoteCommand;
import Commands.RollDiceCommand;
import music.MusicManager;
import music.MusicPlayer;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.entities.impl.MessageEmbedImpl;
import net.dv8tion.jda.core.events.message.MessageEmbedEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import pokemon.BattleModel;

public class App 
{
	private static final char[][] CommandArray = null;
	public static final CommandParser parser = new CommandParser();
	
	public static HashMap<String, Command> commands = new HashMap<String, Command>();
	
    public static void main( String[] args ) throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException
    {
        JDA bot = new JDABuilder(AccountType.BOT).setToken(inputToken()).buildBlocking(); //SET TOKEN
        bot.addEventListener(new BotListener());
        commands.put("ping", new PingCommand());
        commands.put("quote", new QuoteCommand());
        commands.put("song", new PlaySongCommand());
        commands.put("rolldice", new RollDiceCommand());
        commands.put("flipcoin", new FlipCoinCommand());
        commands.put("pokemonbattle", new PokemonBattleCommand());

    }
    
    public static void handleCommand(CommandParser.CommandContainer cmd)
    {
    	if(commands.containsKey(cmd.invoke))
    	{
    		boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.e);
    		if(safe)
    		{
    			commands.get(cmd.invoke).action(cmd.args, cmd.e);
    			commands.get(cmd.invoke).executed(safe, cmd.e);
    		}
    		else 
    		{
    			commands.get(cmd.invoke).executed(safe, cmd.e);
    		}
    	}
    }
    private static String inputToken()
    {
    	System.out.println("Enter your bot token: ");
    	Scanner scanner = new Scanner(System.in);
    	return scanner.nextLine();
    }    
    /*

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
        	System.out.println(objMsg.getContent());
        	commandarray = objMsg.getContent().split(" ", 2);
            if (objMsg.getContent().substring(0,2).equals("//"))
            {
            	callCommand(commandarray, objMsg, objChannel, objUser, objTextChannel, objGuild);
            }
        }
        catch(StringIndexOutOfBoundsException exception)
        {}
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

    */
   
}


