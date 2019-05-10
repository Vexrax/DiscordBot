package Commands;

import net.dv8tion.jda.core.entities.MessageChannel;
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
		String[] stringdict = {CommandStrings.getInstance().HEADS, CommandStrings.getInstance().TAILS};
    	int Random = (int)(Math.random()*2);
		objChannel.sendMessage(String.format("%s %s", CommandStrings.getInstance().COIN_LANDED, stringdict[Random])).queue();
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
