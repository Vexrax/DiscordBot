package Commands;

import java.io.*;
import java.util.*;

import Backend.Ranks;
import Backend.Util;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class QuoteCommand implements Command
{
	private ArrayList<String> valid_commands = new ArrayList<String>();
	private final String HELP = "Usage: = ~//quote";
	String quoteFilePath = "quoteFile.txt";
	private Util Utility = new Util();

	private boolean quoteBeingAddedOrRemoved = false;
	private int requiredVotes = 4;
	private int currentVotes = 0;
	private List<String> votedList = new ArrayList<String>();
	private int voteTime = 120000;

	public QuoteCommand()
	{
		valid_commands.add("add");
		valid_commands.add("remove");
		valid_commands.add("vote");
	}

	public boolean called(String[] args, MessageReceivedEvent event) {
		if (args.length == 0) {
			return true;
		}
		return  valid_commands.contains(args[0]);

	}

	public void action(String[] args, MessageReceivedEvent e)
	{
		if(args.length == 0)
		{
			sendQuote(e);
			return;
		}
		if(args[0].equals(valid_commands.get(0))) //add
		{
			String quote = ConvertArgListToSingleString(args, 1);
			addQuote(e, quote);
		}
		if(args[0].equals(valid_commands.get(1)))
		{
			String quote = ConvertArgListToSingleString(args, 1);
			removeQuote(e,quote);
		}
		if(args[0].equals(valid_commands.get(2)))  //vote
		{
			if(args.length == 2 && args[1].equals("force"))
			{
				Vote(e, args[1].equals("force"));
			}
		}
	}

	private String ConvertArgListToSingleString(String[] args, int startindex) {
		StringBuilder builder = new StringBuilder();
		for(int i = startindex; i < args.length; i++)
		{
			builder.append(args[i] + " ");
		}
		return builder.toString();
	}

	public String help() {
		return HELP;
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}
	public void removeQuote(MessageReceivedEvent e, String quoteToRemove)
	{
		MessageChannel objChannel = e.getChannel();
		//objChannel.sendMessage("This feature isnt ready yet!").queue();
		//if(true)return;

		if (isVoteInProgress(objChannel)) return;
		if(!isQuoteInFile(quoteToRemove))
		{
			objChannel.sendMessage("Quote not found in quote file, quote must be formatted exactly how it appears in the file.").queue();
			return;
		}
		SetUpVoteForQuoteBeingRemoved(quoteToRemove, objChannel);
	}

	public void sendQuote(MessageReceivedEvent e)
	{
		MessageChannel objChannel = e.getChannel();
		try
		{
			int numberoflines = Utility.getFileLineLength(quoteFilePath);
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
	private void Vote(MessageReceivedEvent e, boolean force)
	{
		final MessageChannel objChannel = e.getChannel();
		if(force && Utility.getUserRank(e.getAuthor().getId()) != Ranks.Admin)
		{
			objChannel.sendMessage("You cannot force the vote to pass.").queue();
		}
		if(quoteBeingAddedOrRemoved)
		{
			if(!votedList.contains(e.getAuthor().getId()))
			{
				addVote(e, force, objChannel);
				return;
			}
			objChannel.sendMessage("You have already voted").queue();
			return;
		}
		objChannel.sendMessage("No vote currently being conducted").queue();

	}



	private void addVote(MessageReceivedEvent e, boolean force, MessageChannel objChannel)
	{
		votedList.add(e.getAuthor().getId());
		if((Utility.getUserRank(e.getAuthor().getId()) == Ranks.Admin) && force)
		{
			objChannel.sendMessage(e.getAuthor().getName() + " is forcing the vote to pass").queue();
			currentVotes += requiredVotes + 1;
			objChannel.sendMessage("Current Votes: " + currentVotes + " Required Votes: " + requiredVotes).queue();
			return;
		}
		else if(Utility.getUserRank(e.getAuthor().getId()) == Ranks.Terminator)
		{
			currentVotes += 2;
			SendVoteBeingAddedToTally(objChannel);
			return;
		}
		else
		{
			currentVotes += 1;
			SendVoteBeingAddedToTally(objChannel);
			return;
		}
	}

	private void SendVoteBeingAddedToTally(MessageChannel objChannel)
	{
		objChannel.sendMessage("Your vote has been added").queue();
		objChannel.sendMessage("Current Votes: " + currentVotes + " Required Votes: " + requiredVotes).queue();
	}

	public void addQuote(MessageReceivedEvent e, final String QuoteToBeAdded)
	{
		final MessageChannel objChannel = e.getChannel();
		if (isVoteInProgress(objChannel)) return;
		SetUpVoteForQuoteBeingAdded(QuoteToBeAdded, objChannel);
	}

	private boolean isVoteInProgress(MessageChannel objChannel) {
		if(quoteBeingAddedOrRemoved)
		{
			objChannel.sendMessage("A vote is already in progress please wait for the vote to finish before adding a quote!").queue();
			return true;
		}
		return false;
	}
	private void SetUpVoteForQuoteBeingRemoved(final String QuoteToBeRemoved, final MessageChannel objChannel)
	{
		quoteBeingAddedOrRemoved = true;
		objChannel.sendMessage("A vote to REMOVE the quote:\n " +
				" " + QuoteToBeRemoved + " has been started\n " +
				"Use //quote vote for the quote.\n A minimum of "
				+ requiredVotes + " vote(s) is needed for the vote to pass!").queue();
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if(currentVotes >= requiredVotes)
				{
					removeQuoteFromFile(objChannel, QuoteToBeRemoved);
					objChannel.sendMessage("The vote passed, quote being removed from quote list...").queue();
				}
				else
				{
					objChannel.sendMessage("Not enough votes received for the vote to pass!").queue();
				}
				resetVoting();
				quoteBeingAddedOrRemoved = false;
			}
		}, voteTime);
	}


	private void SetUpVoteForQuoteBeingAdded(final String QuoteToBeAdded, final MessageChannel objChannel)
	{
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
		}, voteTime);
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

	private boolean isQuoteInFile(String quoteToRemove)
	{
		try
		{
			String trimmedQuoteToRemove = quoteToRemove.trim();
			File inputFile = new File(quoteFilePath);
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			String currentLine;
			while ((currentLine = reader.readLine()) != null)
			{
				String trimmedLine = currentLine.trim();
				if (trimmedLine.equals(trimmedQuoteToRemove))
					return true;
			}
			reader.close();
		}
		catch (IOException exception)
		{

		}
		return false;
	}

	public void removeQuoteFromFile(MessageChannel objChannel, String quoteToRemove)
	{
		try
		{
			File inputFile = new File(quoteFilePath);
			File tempFile = new File("myTempFile.txt");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			String currentLine;

			while ((currentLine = reader.readLine()) != null) {
				String trimmedQuoteToRemove = quoteToRemove.trim();
				String trimmedLine = currentLine.trim();
				if (trimmedLine.equals(trimmedQuoteToRemove)) continue;
				writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close();
			reader.close();
			tempFile.renameTo(inputFile);
			
		}
		catch (Exception exception)
		{
			objChannel.sendMessage("An Error occured: " + exception.toString()).queue();
		}
	}

	public void ListAllQuotes(MessageChannel objChannel)
	{
		//TODO, Stub for listing all the quotes
	}
}
