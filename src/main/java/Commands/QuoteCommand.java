package Commands;

import java.io.*;
import java.util.*;

import Backend.Ranks;
import Backend.Util;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class QuoteCommand implements Command
{
	private String[] valid_commands = {"add", "remove", "vote"};
	private final String HELP = "Usage: = ~//quote";
	String quoteFilePath = "quoteFile.txt";

	private boolean quoteBeingAddedOrRemoved = false;
	private int requiredVotes = 4;
	private int currentVotes = 0;
	private List<String> votedList = new ArrayList<String>();
	Util Utility = new Util();

	public boolean called(String[] args, MessageReceivedEvent event) {
		if (args.length == 0) {
			return true;
		}
		if (args.length >= 1 && Arrays.asList(valid_commands).contains(args[0])) {
			return true;
		}
		return false;
	}

	public void action(String[] args, MessageReceivedEvent e)
	{
		System.out.println(args);
		if(args.length == 0)
		{
			sendQuote(e);
			return;
		}
		if(args[0].equals(valid_commands[0])) //add
		{
			StringBuilder builder = new StringBuilder();
			for(int i = 1; i < args.length; i++)
			{
				builder.append(args[i] + " ");
			}
			addQuote(e, builder.toString());
		}
		if(args[0].equals(valid_commands[2]))  //vote
		{
			if(args.length == 2 && args[1].equals("force"))
			{
				addVote(e, true);
				return;
			}
			addVote(e, false);

		}
	}

	public String help() {
		// TODO Auto-generated method stub
		return HELP;
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}
	public void sendQuote(MessageReceivedEvent e)
	{
		MessageChannel objChannel = e.getChannel();
		try
		{
			BufferedReader textreader = new BufferedReader(new FileReader(quoteFilePath));
			int numberoflines = 0;
			while(textreader.readLine() != null)
			{
				numberoflines ++;
			}
			textreader.close();
			String[] quotearray = new String[numberoflines];
			BufferedReader textreaderforquotes = new BufferedReader(new FileReader(quoteFilePath));
			for(int i = 0; i < numberoflines; i++)
			{
				quotearray[i] = textreaderforquotes.readLine();
			}
			objChannel.sendMessage(quotearray[(int)(Math.random()*numberoflines)]).queue();
			textreaderforquotes.close();

		}
		catch(IOException exception)
		{
			objChannel.sendMessage("Somethng went wrong, missing quotefile string, check App.java").queue();
		}
	}
	private void addVote(MessageReceivedEvent e, boolean force)
	{
		final MessageChannel objChannel = e.getChannel();

		if(quoteBeingAddedOrRemoved)
		{
			if(force)
			{
				if(Utility.getUserRank(e.getAuthor().getId()) == Ranks.Admin)
				{
					objChannel.sendMessage(e.getAuthor().getName() + " is forcing the vote to pass").queue();
					currentVotes += requiredVotes + 1;
					objChannel.sendMessage("Current Votes: " + currentVotes + " Required Votes: " + requiredVotes).queue();
					return;
				}
				else
				{
					objChannel.sendMessage("You cannot force the vote.").queue();
				}
				return;
			}
			if(!votedList.contains(e.getAuthor().getId()))
			{
				votedList.add(e.getAuthor().getId());
				currentVotes += 1;
				objChannel.sendMessage("Your vote has been added").queue();
				objChannel.sendMessage("Current Votes: " + currentVotes + " Required Votes: " + requiredVotes).queue();
				return;
			}
			objChannel.sendMessage("You have already voted").queue();
			return;
		}
		objChannel.sendMessage("No vote currently being conducted").queue();

	}

	public void addQuote(MessageReceivedEvent e, final String QuoteToBeAdded)
	{
		final MessageChannel objChannel = e.getChannel();
		System.out.println(QuoteToBeAdded);
		if(quoteBeingAddedOrRemoved)
		{
			objChannel.sendMessage("A vote is already in progress please wait for the vote to finish before adding a quote!").queue();
			return;
		}
		quoteBeingAddedOrRemoved = true;
		objChannel.sendMessage("A vote for the quote:\n " +
				"' " + QuoteToBeAdded + "' has been started\n " +
				"Use //quote vote for the quote.\n A minimum of "
				+ requiredVotes + " vote(s) is needed for the vote to pass!").queue();
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if(currentVotes >= requiredVotes)
				{
					addQuoteToFile(objChannel, QuoteToBeAdded);
					objChannel.sendMessage("The vote passed, quote being added to quote list...").queue();
				}
				else
				{
					objChannel.sendMessage("Not enough votes received for the vote to pass!").queue();
				}
				resetVoting();
				quoteBeingAddedOrRemoved = false;
			}
		}, 60000);
	}
	private void resetVoting()
	{
		currentVotes = 0;
		votedList = new ArrayList<String>();
	}
	private void addQuoteToFile(MessageChannel channel, String QuoteToBeAdded)
	{
		try
		{
			Writer output;
			output = new BufferedWriter(new FileWriter(quoteFilePath, true));
			output.append("\r\n" + QuoteToBeAdded);
			output.close();
		}
		catch (IOException e)
		{
			channel.sendMessage("IO Exception Caught!").queue();
		}
	}
}
