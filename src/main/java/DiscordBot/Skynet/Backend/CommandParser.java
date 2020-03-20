package DiscordBot.Skynet.Backend;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;

public class CommandParser 
{
	public CommandContainer parse(String rw, MessageReceivedEvent e)
	{
		ArrayList<String> split = new ArrayList<String>();
		String raw = rw;
		String cleanedString = raw.replaceFirst("//", "");
		String[] splitCleanedString = cleanedString.split(" ");
		for(String s : splitCleanedString)
		{
			split.add(s);
		}
		String invoke = split.get(0);
		String[] args = new String[split.size()-1];
		split.subList(1, split.size()).toArray(args);
		
		return new CommandContainer(raw, cleanedString, splitCleanedString, invoke, args, e);
	}
	public class CommandContainer
	{
		public final String raw;
		public final String cleanedString;
		public final String[] splitCleanedString;
		public final String invoke;
		public final String[] args;
		public final MessageReceivedEvent e;
		
		public CommandContainer(String raw, String cleanedString, String[] splitCleanString, String invoke, String[] args, MessageReceivedEvent e)
		{
			this.raw = raw;
			this.cleanedString = cleanedString;
			this.splitCleanedString = splitCleanString;
			this.invoke = invoke;
			this.args = args;
			this.e = e;
		}
	}
}
