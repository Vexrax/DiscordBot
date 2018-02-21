package music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;

import net.dv8tion.jda.core.audio.AudioSendHandler;

public class AudioHandler implements AudioSendHandler
{
	private final AudioPlayer audioplayer;
	private AudioFrame lastframe;
	
	public AudioHandler(AudioPlayer audioplayer)
	{
		this.audioplayer = audioplayer;
	}

	public boolean canProvide() 
	{
		if(this.lastframe == null) 
		{
			this.lastframe = audioplayer.provide();
		}
		return this.lastframe != null;
	}
	
	public byte[] provide20MsAudio()
	{
		byte[] data = canProvide() ? this.lastframe.data : null;
		this.lastframe = null; 	
		return data;
	}

	public boolean isOpus()
	{
		return true;
	}
}
