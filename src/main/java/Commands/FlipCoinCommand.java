package Commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


public class FlipCoinCommand implements Command
{

	public boolean called(String[] args, MessageReceivedEvent e) 
	{
		return true;
	}

	public void action(String[] args, MessageReceivedEvent e) 
	{
        MessageChannel objChannel = e.getChannel();
        User objUser = e.getAuthor();   
		String[] stringdict = {"Heads", "Tails"};
    	int Random = (int)(Math.random()*2);
		objChannel.sendMessage("The Coin Landed on " + stringdict[Random]).queue();
        return;
	}

	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	public void executed(boolean success, MessageReceivedEvent e) {
		// TODO Auto-generated method stub
		
	}

}
