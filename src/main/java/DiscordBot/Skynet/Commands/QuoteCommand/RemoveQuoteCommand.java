package DiscordBot.Skynet.Commands.QuoteCommand;


import DiscordBot.Skynet.Backend.Util;
import DiscordBot.Skynet.Commands.Command;
import com.mongodb.client.MongoClient;
import com.mongodb.client.result.DeleteResult;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.bson.Document;

import java.util.Timer;
import java.util.TimerTask;

import static DiscordBot.Skynet.Commands.QuoteCommand.QuoteCommand.*;

public class RemoveQuoteCommand implements Command
{
    Util util = new Util();
    public boolean called(String[] args, MessageReceivedEvent e)
    {
        return args.length > 0;
    }

    public void action(String[] args, MessageReceivedEvent e)
    {
        removeQuote(e, util.convertArgListToSingleString(args, 0));
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

        setUpVoteForQuoteBeingRemoved(quoteToRemove, objChannel);
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
                    removeQuoteFromDatabase(objChannel, QuoteToBeRemoved);
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

    public void removeQuoteFromDatabase(MessageChannel objChannel, String quoteToRemove)
    {
        MongoClient mongoClient = util.provideMongo();
        DeleteResult deleteResult = mongoClient.getDatabase("Skynet").getCollection("Quotes").deleteOne(new Document("quote", quoteToRemove));
        if(deleteResult.getDeletedCount() > 0) {
            objChannel.sendMessage("Removing quote from list").queue();
        } else {
            objChannel.sendMessage("Quote could not be found").queue();
        }
    }
}
