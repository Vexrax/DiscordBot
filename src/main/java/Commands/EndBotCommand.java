package Commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class EndBotCommand implements Command
{

	public boolean called(String[] args, MessageReceivedEvent e) {
		if(e.getAuthor().getName().equals("vexrax") && (e.getAuthor().getId().equals("188313190214533120")))
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
