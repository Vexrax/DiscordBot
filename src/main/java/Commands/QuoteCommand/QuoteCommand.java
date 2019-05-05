package Commands.QuoteCommand;

import java.util.*;

import Backend.Util;
import Commands.Command;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class QuoteCommand implements Command
{
	private final String HELP = "Usage: = ~//quote";
	protected static String quoteFilePath = "quoteFile.txt";

	public static HashMap<String, Command> commands = new HashMap<String, Command>();

	protected static boolean quoteBeingAddedOrRemoved = false;
	protected static int requiredVotes = 4;
	protected  static int currentVotes = 0;
	protected static int voteTime = 120000;

	protected static List<String> votedList = new ArrayList<String>();


	public QuoteCommand()
	{
		commands.put("add", new AddQuoteCommand());
		commands.put("remove", new RemoveQuoteCommand());
		commands.put("vote", new VoteQuoteCommand());
		commands.put("", new SendQuoteCommand());
		commands.put("list", new ListQuoteCommand());

	}

	public boolean called(String[] args, MessageReceivedEvent event) {
		if (args.length == 0) {
			return true;
		}
		return commands.containsKey(args[0]);
	}

	public void action(String[] args, MessageReceivedEvent e)
	{
		String command;
		if(args.length > 0)
		{
			command  = args[0];
		}
		else
		{
			command = "";
		}
		String[] cleanedArgs = Util.CleanArgs(args);
		boolean safe = commands.get(command).called(cleanedArgs, e);
		if(safe)
		{
			commands.get(command).action(cleanedArgs, e);
			commands.get(command).executed(safe, e);
		}
		else
		{
			commands.get(command).executed(safe, e);
		}
	}

	public String help() {
		return HELP;
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

	protected static boolean isVoteInProgress(MessageChannel objChannel) {
		if(quoteBeingAddedOrRemoved)
		{
			objChannel.sendMessage("A vote is already in progress please wait for the vote to finish before adding a quote!").queue();
			return true;
		}
		return false;
	}

	public static void resetVoting()
	{
		currentVotes = 0;
		votedList = new ArrayList<String>();
	}

}
