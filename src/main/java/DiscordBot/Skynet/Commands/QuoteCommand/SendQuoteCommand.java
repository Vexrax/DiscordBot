package DiscordBot.Skynet.Commands.QuoteCommand;


import DiscordBot.Skynet.Backend.Util;
import DiscordBot.Skynet.Commands.Command;

import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.bson.Document;

import javax.print.Doc;
import java.util.Arrays;

public class SendQuoteCommand implements Command
{
    Util util = new Util();
    public boolean called(String[] args, MessageReceivedEvent e) {
        return true;
    }

    public void action(String[] args, MessageReceivedEvent e)
    {
        sendQuoteToChannel(e);
    }

    public String help() {
        return null;
    }

    public void executed(boolean success, MessageReceivedEvent e) {

    }

    public void sendQuoteToChannel(MessageReceivedEvent e)
    {
        MessageChannel objChannel = e.getChannel();
        MongoClient mongoClient = util.provideMongo();
        MongoCollection<Document> results = mongoClient.getDatabase("Skynet").getCollection("Quotes");
        Document randomQuote = results.aggregate(Arrays.asList(Aggregates.sample(1))).first();
        String output = String.format("'%s' -%s %s %s",
                randomQuote.get("quote"),
                randomQuote.get("author"),
                randomQuote.get("context"),
                randomQuote.get("year")
                );
        objChannel.sendMessage(output).queue();


    }
}
