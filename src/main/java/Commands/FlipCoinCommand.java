package Commands;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class FlipCoinCommand implements Command
{

	public boolean called(String[] args, MessageReceivedEvent e) 
	{
		if(args[0].equals("heads") || args[0].equals("tails"))
			return true;
		return false;
	}

	public void action(String[] args, MessageReceivedEvent e) 
	{
        MessageChannel objChannel = e.getChannel();
        User objUser = e.getAuthor();   

    	int Random = (int)(Math.random()*2);
        if (Random == 1 && args[0].equals("heads") || Random == 0 && args[0].equals("tails"))
        {
        	objChannel.sendMessage(objUser.getName() + " called the side CORRECTLY").queue();
        	return;
        }
        objChannel.sendMessage(objUser.getName() + " called the side INCORRECTLY").queue();
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
