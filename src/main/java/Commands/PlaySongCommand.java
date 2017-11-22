package Commands;


import Backend.Search;
import music.MusicManager;
import music.MusicPlayer;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.entities.Guild;

public class PlaySongCommand implements Command
{
	private final String API_KEY;
	public PlaySongCommand(String YoutubeApiKey)
	{
		this.API_KEY = YoutubeApiKey;
	}
	final MusicManager manager = new MusicManager();
	public boolean called(String[] args, MessageReceivedEvent event) 
	{
		if(args[0].equals("play") || args[0].equals("skip") || args[0].equals("clear"))
			return true;
		return false;
	}

	public void action(String[] args, MessageReceivedEvent e) 
	{		
		if(args[0].equals("play"))
		{			
			StringBuilder builder = new StringBuilder();
			for(int i = 1; i < args.length; i++)
			{
				builder.append(args[i] + " ");
			}
			play(builder.toString(), e);
		}
		else if(args[0].equals("skip"))
		{
			skipSong(e);
		}
		else if(args[0].equals("clear"))
		{
			clearPlaylist(e);
		}
	}

	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}
	public void play(String song, MessageReceivedEvent e)
	{
        User objUser = e.getAuthor();   
        TextChannel objTextChannel = e.getTextChannel();
        Guild objGuild = e.getGuild();
        Search url = new Search();
        String urlToPlay = "https://www.youtube.com/watch?v=" + url.searchToUrl(song, this.API_KEY);
		if(!objGuild.getAudioManager().isConnected() && !objGuild.getAudioManager().isAttemptingToConnect())
		{
			VoiceChannel voicechannel = objGuild.getMember(objUser).getVoiceState().getChannel();
			if(voicechannel == null)
			{
					objTextChannel.sendMessage("You must join a channel first").queue();
			}
			objGuild.getAudioManager().openAudioConnection(voicechannel);
		}
		manager.loadTrack(objTextChannel, urlToPlay);
	}
	public void skipSong(MessageReceivedEvent e)
	{
        MessageChannel objChannel = e.getChannel();  
        TextChannel objTextChannel = e.getTextChannel();
    	if(!e.getGuild().getAudioManager().isConnected() && !e.getGuild().getAudioManager().isAttemptingToConnect())
    	{
    		objTextChannel.sendMessage("Player is not currently playing song.").queue();
    		return;
    	}
    	manager.getPlayer(e.getGuild()).skipTrack();
    	objTextChannel.sendMessage("Skipping current song").queue();
	}
	
	public void clearPlaylist(MessageReceivedEvent e)
	{
        TextChannel objTextChannel = e.getTextChannel();
    	MusicPlayer player = manager.getPlayer(objTextChannel.getGuild());
    	if(player.getListener().getTracks().isEmpty())
    	{
    		objTextChannel.sendMessage("Your playlist is already empty.").queue();
    		return;
    	}
    	player.getListener().getTracks().clear();
    	manager.getPlayer(e.getGuild()).skipTrack();
    	objTextChannel.sendMessage("Playlist cleared.").queue();
    	
	}

}
