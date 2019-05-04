package Commands.QuoteCommand;

import Backend.Util;
import Commands.Command;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

import static Commands.QuoteCommand.QuoteCommand.*;

public class AddQuoteCommand implements Command
{
    Util util = new Util();

    public boolean called(String[] args, MessageReceivedEvent e)
    {
        return args.length > 0;
    }

    public void action(String[] args, MessageReceivedEvent e)
    {
        addQuote(e, util.ConvertArgListToSingleString(args, 0));
    }

    public String help()
    {
        return null;
    }

    public void executed(boolean success, MessageReceivedEvent e)
    {

    }

    public void addQuote(MessageReceivedEvent e, final String QuoteToBeAdded)
    {
        final MessageChannel objChannel = e.getChannel();
        if (isVoteInProgress(objChannel)) return;
        SetUpVoteForQuoteBeingAdded(QuoteToBeAdded, objChannel);
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

    private void addQuoteToFile(MessageChannel channel, String QuoteToBeAdded)
    {
        try
        {
            int numberoflines = util.getFileLineLength(quoteFilePath);
            String[] quotearray = new String[numberoflines];
            BufferedReader textreaderforquotes = new BufferedReader(new FileReader(quoteFilePath));
            for(int i = 0; i < numberoflines; i++)
            {
                quotearray[i] = textreaderforquotes.readLine();
            }
        }
        catch (IOException e)
        {
            channel.sendMessage("IO Exception Caught!").queue();
        }
    }
}
