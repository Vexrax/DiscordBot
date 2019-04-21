package Commands;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import Backend.Search;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import music.MusicManager;
import music.MusicPlayer;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.entities.Guild;



public class PlaySongCommand implements Command
{
	private final String API_KEY;
    private User objUser;  
    private TextChannel objTextChannel;
    private Guild objGuild;
	private MusicManager manager = new MusicManager();
	private ArrayList<String> valid_commands;

	public PlaySongCommand(String YoutubeApiKey)
	{
		valid_commands = new ArrayList<String>();
		valid_commands.add(CommandStrings.getInstance().COMMAND_SONG_PLAY);
		valid_commands.add(CommandStrings.getInstance().COMMAND_SONG_CLEAR);
		valid_commands.add(CommandStrings.getInstance().COMMAND_SONG_BANISH);
		valid_commands.add(CommandStrings.getInstance().COMMAND_SONG_GET);
		this.API_KEY = YoutubeApiKey;
	}
	public boolean called(String[] args, MessageReceivedEvent e) 
	{
        objUser = e.getAuthor();   
        objTextChannel = e.getTextChannel();
        objGuild = e.getGuild();
        if(args.length == 0)
        {
			objTextChannel.sendMessage(help()).queue();
			return false;
		}
		return valid_commands.contains(args[0]);

	}

	public void action(String[] args, MessageReceivedEvent e) 
	{
		System.out.println(args[0]);
		if(args[0].equals(CommandStrings.getInstance().COMMAND_SONG_PLAY))
		{			
			if(!args[1].startsWith("https://www.youtube.com/watch?v="))
			{
				StringBuilder builder = new StringBuilder();
				for(int i = 1; i < args.length; i++)
				{
					builder.append(args[i] + " ");
				}
				Search url = new Search();
	        	String urlToPlay = "https://www.youtube.com/watch?v=" + url.searchToUrl(builder.toString() + "lyrics", this.API_KEY);
				play(urlToPlay, e);
			}
			else
			{
				play(args[1], e);
			}
		}
		else if(args[0].equals(CommandStrings.getInstance().COMMAND_SONG_SKIP))
		{
			skipSong(e);
		}
		else if(args[0].equals(CommandStrings.getInstance().COMMAND_SONG_CLEAR))
		{
			clearPlaylist(e);
		}
		else if(args[0].equals(CommandStrings.getInstance().COMMAND_SONG_BANISH))
		{
			banish(e);
		}
		else if(args[0].equals(CommandStrings.getInstance().COMMAND_SONG_GET))
		{
			if(args[1].equals(CommandStrings.getInstance().COMMAND_SONG_PLAYLIST))
			{
				getPlaylist(e);
			}
			else if(args[1].equals(CommandStrings.getInstance().COMMAND_SONG_CURRENT))
			{
				getCurrentSong(e);
			}
		}
	}

	public String help() {
		return CommandStrings.getInstance().COMMAND_SONG_HELP;
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}
	public void play(String song, MessageReceivedEvent e)
	{
		
		System.out.println(song);
		if(objGuild == null)
			return;
        if(!objGuild.getAudioManager().isConnected() && !objGuild.getAudioManager().isAttemptingToConnect())
		{
			VoiceChannel voicechannel = objGuild.getMember(objUser).getVoiceState().getChannel();
			if(voicechannel == null)
			{
					objTextChannel.sendMessage(CommandStrings.getInstance().COMMAND_SONG_TEXT_JOINCHANNEL).queue();
			}
			objGuild.getAudioManager().openAudioConnection(voicechannel);
		}
		manager.loadTrack(objTextChannel, song);
	}
	public void skipSong(MessageReceivedEvent e)
	{
    	if(!objGuild.getAudioManager().isConnected() && !objGuild.getAudioManager().isAttemptingToConnect())
    	{
    		objTextChannel.sendMessage(CommandStrings.getInstance().COMMAND_SONG_TEXT_NOTPLAYING).queue();
    		return;
    	}
    	manager.getPlayer(objGuild).skipTrack();
    	if(manager.getPlayer(objGuild).getListener().getTrackSize() == 0)
    	{
    		this.manager = new MusicManager();
		}
    	objTextChannel.sendMessage(CommandStrings.getInstance().COMMAND_SONG_TEXT_SKIPPING).queue();
	}
	
	public void clearPlaylist(MessageReceivedEvent e)
	{
    	MusicPlayer player = manager.getPlayer(objTextChannel.getGuild());
    	if(player.getListener().getTracks().isEmpty())
    	{
    		objTextChannel.sendMessage(CommandStrings.getInstance().COMMAND_SONG_TEXT_PLAYLISTEMPTY).queue();
    		return;
    	}
    	player.getListener().getTracks().clear();
    	manager.getPlayer(objGuild).skipTrack();
    	this.manager = new MusicManager();
    	objTextChannel.sendMessage(CommandStrings.getInstance().COMMAND_SONG_TEXT_CLEARPLAYLIST).queue();
    	
	}
	public void banish(MessageReceivedEvent e)
	{
		objGuild.getAudioManager().closeAudioConnection();
		objTextChannel.sendMessage(CommandStrings.getInstance().COMMAND_SONG_TEXT_LEAVECHANNEL).queue();
	}
	public void getPlaylist(MessageReceivedEvent e)
	{
		MusicPlayer player = manager.getPlayer(objTextChannel.getGuild());
		EmbedBuilder builder = new EmbedBuilder();
		builder.appendDescription(CommandStrings.getInstance().COMMAND_SONG_TEXT_CURRENTPLAYLIST);
		for (AudioTrack track : player.getListener().getTracks())
		{
			builder.appendDescription(track.getInfo().title + '\n');
			if(builder.getDescriptionBuilder().length() >= 1900)
			{
				objTextChannel.sendMessage(builder.build()).queue();
				builder = new EmbedBuilder();
			}
		}
		objTextChannel.sendMessage(builder.build()).queue();

	}
	public void getCurrentSong(MessageReceivedEvent e)
	{
		objTextChannel.sendMessage(CommandStrings.getInstance().COMMAND_SONG_TEXT_CURRENTSONG).queue();
		objTextChannel.sendMessage(manager.getPlayer(objTextChannel.getGuild()).getAudioPlayer().getPlayingTrack().getInfo().title).queue();
	}

}
