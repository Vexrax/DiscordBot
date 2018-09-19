package Commands;

import Backend.Ranks;
import Backend.Util;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class EndBotCommand implements Command
{
	Util utility = new Util();
	public boolean called(String[] args, MessageReceivedEvent e) {
		if((utility.getUserRank(e.getAuthor().getId())) == Ranks.Admin)
		{
			e.getChannel().sendMessage("Admin of skynet " + e.getAuthor().getName() + " has ended the proccess.").queue();
			return true;
		}
		e.getChannel().sendMessage("This user is not a high enough rank to end me").queue();
		return false;
	}

	public void action(String[] args, MessageReceivedEvent e) 
	{
		System.exit(0);
	}

	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	public void executed(boolean success, MessageReceivedEvent e) {
		// TODO Auto-generated method stub
		
	}

}
