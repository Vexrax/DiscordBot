package Commands.SongCommand;

import Commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class BanishCommand implements Command
{
    public boolean called(String[] args, MessageReceivedEvent e)
    {
        return true;
    }

    public void action(String[] args, MessageReceivedEvent e)
    {
        MusicCommands.getInstance().banish(e);
    }

    public String help()
    {
        return null;
    }

    public void executed(boolean success, MessageReceivedEvent e)
    {

    }
}
