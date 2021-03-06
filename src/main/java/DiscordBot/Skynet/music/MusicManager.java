package DiscordBot.Skynet.music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;

import java.util.HashMap;
import java.util.Map;

public class MusicManager 
{
	private final AudioPlayerManager manager = new DefaultAudioPlayerManager();
	private final Map<String, MusicPlayer> players = new HashMap();
	
	public MusicManager()
	{
		AudioSourceManagers.registerRemoteSources(manager);
		AudioSourceManagers.registerLocalSource(manager);
	}
	public synchronized MusicPlayer getPlayer(Guild guild)
	{
		if(!players.containsKey(guild.getId()))
		{
			players.put(guild.getId(), new MusicPlayer(manager.createPlayer(), guild));
		}
		return players.get(guild.getId());
	}
	public void loadTrack(final TextChannel channel, final String source)
	{
		final MusicPlayer player = getPlayer(channel.getGuild());
		channel.getGuild().getAudioManager().setSendingHandler(player.getAudioHandler());
		this.manager.loadItemOrdered(player, source, new AudioLoadResultHandler()
		{
			public void trackLoaded(AudioTrack track)
			{
				channel.sendMessage(track.getInfo().title + " being added to playlist").queue();
				player.playTrack(track);
			}

			public void loadFailed(FriendlyException exception) 
			{
				channel.sendMessage("an exception has occured " + exception.getMessage()).queue();				
			}

			public void noMatches() 
			{
				channel.sendMessage("failed to find " + source).queue();				
			}

			public void playlistLoaded(AudioPlaylist playlist) 
			{
				StringBuilder builder = new StringBuilder();
				builder.append("adding: ").append(playlist.getName()).append("**\n");
				
				for(int i = 0; i < playlist.getTracks().size() && i < Integer.MAX_VALUE; i++)
				{
					AudioTrack track = playlist.getTracks().get(i);
					builder.append("\n").append("->" + track.getInfo().title + "");
					player.playTrack(track);
					if(builder.length() >= 1900)
					{
						channel.sendMessage(builder.toString()).queue();
						builder = new StringBuilder();
					}
				}
				channel.sendMessage(builder.toString()).queue();
			}
		});
		
	}
}
