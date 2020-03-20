package DiscordBot.Skynet.Commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class PingCommand implements Command
{
	private final String HELP = "Usage: = ~//ping";

	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) 
	{
		event.getTextChannel().sendMessage("Pong").queue();		
	}

	public String help() {
		// TODO Auto-generated method stub
		return HELP;
	}

	public void executed(boolean success, MessageReceivedEvent event) 
	{
		return;
	}

}
