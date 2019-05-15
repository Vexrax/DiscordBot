package Commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class PollCommand implements Command
{
    public boolean called(String[] args, MessageReceivedEvent e) {
        return false;
    }

    public void action(String[] args, MessageReceivedEvent e) {

    }

    public String help() {
        return null;
    }

    public void executed(boolean success, MessageReceivedEvent e) {

    }
}
