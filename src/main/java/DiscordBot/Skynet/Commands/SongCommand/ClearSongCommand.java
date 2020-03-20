package DiscordBot.Skynet.Commands.SongCommand;

import DiscordBot.Skynet.Commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ClearSongCommand implements Command
{
    public boolean called(String[] args, MessageReceivedEvent e)
    {
        return true;
    }

    public void action(String[] args, MessageReceivedEvent e)
    {
        MusicCommands.getInstance().clearPlaylist(e);
    }

    public String help()
    {
        return null;
    }

    public void executed(boolean success, MessageReceivedEvent e)
    {

    }
}
