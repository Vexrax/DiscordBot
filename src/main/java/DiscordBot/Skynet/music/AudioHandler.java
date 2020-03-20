package DiscordBot.Skynet.music;

import com.sedmelluq.discord.lavaplayer.format.OpusAudioDataFormat;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.core.audio.AudioSendHandler;

public class AudioHandler implements AudioSendHandler
{
	private final AudioPlayer audioplayer;
	private AudioFrame lastFrame;
	
	public AudioHandler(AudioPlayer audioplayer)
	{
		this.audioplayer = audioplayer;
	}

	public boolean canProvide() 
	{
		lastFrame = audioplayer.provide();
		return lastFrame != null;
	}
	
	public byte[] provide20MsAudio()
	{
		return lastFrame.getData();
	}

	public boolean isOpus()
	{
		return lastFrame != null && lastFrame.getFormat() instanceof OpusAudioDataFormat;
	}
}
