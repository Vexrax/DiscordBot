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
    private User objUser;  
    private TextChannel objTextChannel;
    private Guild objGuild;
	private MusicManager manager = new MusicManager();

	public PlaySongCommand(String YoutubeApiKey)
	{
		this.API_KEY = YoutubeApiKey;
	}
	public boolean called(String[] args, MessageReceivedEvent e) 
	{
        objUser = e.getAuthor();   
        objTextChannel = e.getTextChannel();
        objGuild = e.getGuild();
		if(args[0].equals("play") || args[0].equals("skip") || args[0].equals("clear"))
			return true;
		return false;
	}

	public void action(String[] args, MessageReceivedEvent e) 
	{		
		if(args[0].equals("play"))
		{			
			if(!args[0].startsWith("https://www.youtube.com/watch?v="))
			{
				StringBuilder builder = new StringBuilder();
				for(int i = 1; i < args.length; i++)
				{
					builder.append(args[i] + " ");
				}
				Search url = new Search();
	        	String urlToPlay = "https://www.youtube.com/watch?v=" + url.searchToUrl(builder.toString(), this.API_KEY);
				play(urlToPlay, e);
			}
			else
			{
				play(args[0], e);
			}
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
		
		System.out.println(song);
		if(objGuild == null)
			return;
		
        if(!objGuild.getAudioManager().isConnected() && !objGuild.getAudioManager().isAttemptingToConnect())
		{
			VoiceChannel voicechannel = objGuild.getMember(objUser).getVoiceState().getChannel();
			if(voicechannel == null)
			{
					objTextChannel.sendMessage("You must join a channel first").queue();
			}
			objGuild.getAudioManager().openAudioConnection(voicechannel);
		}
		manager.loadTrack(objTextChannel, song);
	}
	public void skipSong(MessageReceivedEvent e)
	{
    	if(!objGuild.getAudioManager().isConnected() && !objGuild.getAudioManager().isAttemptingToConnect())
    	{
    		objTextChannel.sendMessage("Player is not currently playing song.").queue();
    		return;
    	}
    	manager.getPlayer(objGuild).skipTrack();
    	if(manager.getPlayer(objGuild).getListener().getTrackSize() == 0)
    	{
    		this.manager = new MusicManager();
    	}
    	objTextChannel.sendMessage("Skipping current song").queue();
	}
	
	public void clearPlaylist(MessageReceivedEvent e)
	{
    	MusicPlayer player = manager.getPlayer(objTextChannel.getGuild());
    	if(player.getListener().getTracks().isEmpty())
    	{
    		objTextChannel.sendMessage("Your playlist is already empty.").queue();
    		return;
    	}
    	player.getListener().getTracks().clear();
    	manager.getPlayer(objGuild).skipTrack();
    	this.manager = new MusicManager();
    	objTextChannel.sendMessage("Playlist cleared.").queue();
    	
	}

}
