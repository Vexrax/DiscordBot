package DiscordBot.Skynet.Commands;

import DiscordBot.Skynet.Backend.Ranks;
import DiscordBot.Skynet.Backend.Util;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class EndBotCommand implements Command
{
	Util utility = new Util();
	public boolean called(String[] args, MessageReceivedEvent e) {
		if((utility.getUserRank(e.getAuthor().getId())) == Ranks.Admin)
		{
			e.getChannel().sendMessage(String.format("%s %s %s.", CommandStrings.getInstance().ADMIN_OF_SKYNET, e.getAuthor().getName(), CommandStrings.getInstance().ENDED_PROCCESS)).queue();
			return true;
		}
		e.getChannel().sendMessage(CommandStrings.getInstance().RANK_NOT_HIGH_ENOUGH_TO_END).queue();
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
