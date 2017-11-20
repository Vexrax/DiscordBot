package Commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RollDiceCommand implements Command
{

	public boolean called(String[] args, MessageReceivedEvent e) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent e)
	{
        MessageChannel objChannel = e.getChannel();
    	objChannel.sendMessage("You rolled a " + (int)(Math.random()*6)).queue();
		
	}

	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	public void executed(boolean success, MessageReceivedEvent e) {
		// TODO Auto-generated method stub
		
	}

}
