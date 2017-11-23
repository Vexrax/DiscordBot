package Commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ChatBotTensorFlowCommand implements Command {

	public boolean called(String[] args, MessageReceivedEvent e) {
		if(args[0].equals("train"))
		{	
			return true;		
		} 
		return false;
	}

	public void action(String[] args, MessageReceivedEvent e) 
	{
		if(!e.getMessage().getMentionedChannels().isEmpty())
			e.getChannel().sendMessage("Began training on " + e.getMessage().getMentionedChannels().get(0).getName()).queue();
		else
			e.getChannel().sendMessage("please select a channel");
	}

	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	public void executed(boolean success, MessageReceivedEvent e) {
		// TODO Auto-generated method stub
		
	}

}
