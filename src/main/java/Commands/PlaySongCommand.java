package Commands;


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
	final MusicManager manager = new MusicManager();
	public boolean called(String[] args, MessageReceivedEvent event) 
	{
		System.out.println("here");

		return true;
	}

	public void action(String[] args, MessageReceivedEvent e) 
	{		
		System.out.println(args[1]);
		if(args[0].equals("play"))
		{			
			play(args[1], e);
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
        MessageChannel objChannel = e.getChannel();  
        TextChannel objTextChannel = e.getTextChannel();
    	MusicPlayer player = manager.getPlayer(objTextChannel.getGuild());
    	if(player.getListener().getTracks().isEmpty())
    	{
    		objTextChannel.sendMessage("Your playlist is already empty.").queue();
    		return;
    	}
    	player.getListener().getTracks().clear();
    	objTextChannel.sendMessage("Playlist cleared.").queue();
    	
	}

}
