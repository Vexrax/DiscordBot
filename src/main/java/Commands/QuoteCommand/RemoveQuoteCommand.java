package Commands.QuoteCommand;

import Backend.Util;
import Commands.Command;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

import static Commands.QuoteCommand.QuoteCommand.*;

public class RemoveQuoteCommand implements Command
{
    Util util = new Util();
    public boolean called(String[] args, MessageReceivedEvent e)
    {
        return args.length > 0;
    }

    public void action(String[] args, MessageReceivedEvent e)
    {
        removeQuote(e, util.ConvertArgListToSingleString(args, 0));
    }

    public String help() {
        return null;
    }

    public void executed(boolean success, MessageReceivedEvent e) {

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
        setUpVoteForQuoteBeingRemoved(quoteToRemove, objChannel);
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

    private void setUpVoteForQuoteBeingRemoved(final String QuoteToBeRemoved, final MessageChannel objChannel)
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
}
