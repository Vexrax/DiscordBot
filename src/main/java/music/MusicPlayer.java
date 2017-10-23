package music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.core.entities.Guild;

public class MusicPlayer 
{
	private final AudioPlayer audioplayer;
	private final AudioListener listener;
	private final Guild guild;
	
	public MusicPlayer(AudioPlayer audioplayer, Guild guild)
	{
		this.audioplayer = audioplayer;
		this.guild = guild;
		this.listener = new AudioListener(this);
		audioplayer.addListener(listener);
	}
	
	public AudioPlayer getAudioPlayer()
	{
		return this.audioplayer;
	}
	
	public Guild getGuild()
	{
		return this.guild;
	}
	
	public AudioListener getListener()
	{
		return this.listener;
	}
	
	public AudioHandler getAudioHandler() 
	{
		return new AudioHandler(this.audioplayer);
	}
	
	public synchronized void playTrack(AudioTrack track)
	{
		listener.queue(track);
	}
	
	public synchronized void skipTrack() 
	{
		listener.nextTrack();
	}


}
