package DiscordBot.Skynet.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class AudioListener extends AudioEventAdapter
{
	private final BlockingQueue<AudioTrack> tracks = new LinkedBlockingQueue<AudioTrack>();
	private final MusicPlayer player;

	public AudioListener(MusicPlayer player)
	{
		this.player = player;
	}
	public BlockingQueue<AudioTrack> getTracks()
	{
		return this.tracks;
	}
	public int getTrackSize()
	{
		return this.tracks.size();
	}

	public  void nextTrack()
	{
		if(tracks.isEmpty())
		{
			if(player.getGuild().getAudioManager().getConnectedChannel() != null)
			{
				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						if (tracks.size() <= 0) {
							player.getGuild().getAudioManager().closeAudioConnection();
						}
					}
				}, 500);
			}
			return;
		}
		player.getAudioPlayer().startTrack(tracks.poll(), false);
	}
	@Override
	public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason)
	{
			if(endReason.mayStartNext)
				nextTrack();
	}
	public void queue(AudioTrack track)
	{
		if(!player.getAudioPlayer().startTrack(track, true))
			tracks.offer(track);
	}
	public void pause()
	{
		this.player.getAudioPlayer().setPaused(true);
	}
}
