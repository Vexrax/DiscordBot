package Commands.SongCommand;

import Commands.CommandStrings;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import music.MusicManager;
import music.MusicPlayer;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class MusicCommands
{
    private static MusicCommands ourInstance = new MusicCommands();

    public static MusicCommands getInstance()
    {
        return ourInstance;
    }

    private MusicCommands()
    {
    }

    private MusicManager manager = new MusicManager();

    private User objUser;
    private TextChannel objTextChannel;
    private Guild objGuild;

    //Todo: There a bug where if you play song x -> play song y -> skip song -> play song a, it will skip song y
    public void play(String song, MessageReceivedEvent e)
    {
        objUser = e.getAuthor();
        objTextChannel =e.getTextChannel();
        objGuild = e.getGuild();
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
        objUser = e.getAuthor();
        objTextChannel =e.getTextChannel();
        objGuild = e.getGuild();
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
        objUser = e.getAuthor();
        objTextChannel =e.getTextChannel();
        objGuild = e.getGuild();
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
        objUser = e.getAuthor();
        objTextChannel =e.getTextChannel();
        objGuild = e.getGuild();
        objGuild.getAudioManager().closeAudioConnection();
        objTextChannel.sendMessage(CommandStrings.getInstance().COMMAND_SONG_TEXT_LEAVECHANNEL).queue();
    }
    public void getPlaylist(MessageReceivedEvent e)
    {
        objUser = e.getAuthor();
        objTextChannel =e.getTextChannel();
        objGuild = e.getGuild();
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
        objUser = e.getAuthor();
        objTextChannel =e.getTextChannel();
        objGuild = e.getGuild();
        objTextChannel.sendMessage(CommandStrings.getInstance().COMMAND_SONG_TEXT_CURRENTSONG).queue();
        objTextChannel.sendMessage(manager.getPlayer(objTextChannel.getGuild()).getAudioPlayer().getPlayingTrack().getInfo().title).queue();
    }
}
