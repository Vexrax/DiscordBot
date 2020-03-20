package DiscordBot.Skynet.Commands.SongCommand;


import DiscordBot.Skynet.Commands.Command;
import DiscordBot.Skynet.Commands.CommandStrings;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class GetSongCommand implements Command
{

    public boolean called(String[] args, MessageReceivedEvent e)
    {
        return args[0].equals(CommandStrings.getInstance().COMMAND_SONG_PLAYLIST ) || args[0].equals(CommandStrings.getInstance().COMMAND_SONG_CURRENT);
    }

    public void action(String[] args, MessageReceivedEvent e)
    {
        if(args[0].equals(CommandStrings.getInstance().COMMAND_SONG_PLAYLIST))
        {
            MusicCommands.getInstance().getPlaylist(e);
        }
        else if(args[0].equals(CommandStrings.getInstance().COMMAND_SONG_CURRENT))
        {
            MusicCommands.getInstance().getCurrentSong(e);
        }
    }

    public String help() {
        return null;
    }

    public void executed(boolean success, MessageReceivedEvent e)
    {

    }
}
