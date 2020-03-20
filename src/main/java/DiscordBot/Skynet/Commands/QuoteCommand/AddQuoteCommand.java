package DiscordBot.Skynet.Commands.QuoteCommand;


import DiscordBot.Skynet.Backend.Util;
import DiscordBot.Skynet.Commands.Command;
import com.mongodb.client.MongoClient;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.bson.Document;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import static DiscordBot.Skynet.Commands.QuoteCommand.QuoteCommand.*;


public class AddQuoteCommand implements Command
{
    Util util = new Util();

    public boolean called(String[] args, MessageReceivedEvent e)
    {
        String author = "";
        String date = "";
        for(String arg : args) {
            if(arg.startsWith("//") && author.length() == 0) {
                author = arg;
            }
            else if(arg.startsWith("//") && date.length() == 0) {
                date = arg;
            }
        }
        return args.length > 0 && author.length() > 0 && date.length() > 0;
    }

    public void action(String[] args, MessageReceivedEvent e)
    {
        String author = "";
        String date = "";
        StringBuilder quote = new StringBuilder();
        for(String arg : args) {
            if(arg.startsWith("//") && author.length() == 0) {
                author = arg.replace("//", "");
            }
            else if(arg.startsWith("//") && date.length() == 0) {
                date = arg.replace("//", "");
            } else {
                quote.append(arg + " ");
            }
        }
        addQuote(e, quote.toString(), author, date);
    }

    public String help()
    {
        return null;
    }

    public void executed(boolean success, MessageReceivedEvent e)
    {

    }

    public void addQuote(MessageReceivedEvent e, final String QuoteToBeAdded, String author, String date)
    {
        final MessageChannel objChannel = e.getChannel();
        if (isVoteInProgress(objChannel)) return;
        setUpVoteForQuoteBeingAdded(QuoteToBeAdded, author, date, objChannel);
    }

    private void setUpVoteForQuoteBeingAdded(final String QuoteToBeAdded, final String author, final String date, final MessageChannel objChannel)
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
                    addQuoteToDatabase(objChannel, QuoteToBeAdded, author, date);
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

    private void addQuoteToDatabase(MessageChannel channel, String QuoteToBeAdded, String author, String date)
    {
        MongoClient mongoClient = util.provideMongo();
        Document document = Document.parse(formatForMongo(QuoteToBeAdded, author, date).toString());
        mongoClient.getDatabase("Skynet").getCollection("Quotes").insertOne(document);
    }

    private JSONObject formatForMongo(String QuoteToBeAdded, String author, String date) {
        JSONObject document = new JSONObject();
        document.put("quote", QuoteToBeAdded);
        document.put("year", date);
        document.put("author", author);
        document.put("context", ""); // TODO handle context
        return document;
    }

}
