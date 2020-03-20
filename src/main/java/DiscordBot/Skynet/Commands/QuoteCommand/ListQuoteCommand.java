package DiscordBot.Skynet.Commands.QuoteCommand;


import DiscordBot.Skynet.Backend.Ranks;
import DiscordBot.Skynet.Backend.Util;
import DiscordBot.Skynet.Commands.Command;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import static DiscordBot.Skynet.Commands.QuoteCommand.QuoteCommand.quoteFilePath;


public class ListQuoteCommand implements Command {
    Util util = new Util();

    public boolean called(String[] args, MessageReceivedEvent e) {
        return util.getUserRank(e.getAuthor().getId()) == Ranks.Admin;
    }

    public void action(String[] args, MessageReceivedEvent e)
    {
        listAllQuotes(e);
    }

    public String help() {
        return "You must be a Skynet Admin to use this command";
    }

    public void executed(boolean success, MessageReceivedEvent e) {

    }

    private void listAllQuotes(MessageReceivedEvent e) {
        MongoClient mongoClient = util.provideMongo();
        FindIterable<Document> results = mongoClient.getDatabase("Skynet").getCollection("Quotes").find();
        StringBuilder builder = new StringBuilder();
        for(Document doc : results) {
            //Discord only supports messages that are 2000 chars or less
            if(builder.length() >= 1900)
            {
                e.getChannel().sendMessage(builder.toString()).queue();
                builder = new StringBuilder();
            }
            builder.append(String.format("'%s' -%s %s %s",
                    doc.get("quote"),
                    doc.get("author"),
                    doc.get("context"),
                    doc.get("year")) + "\r\n");
        }
        e.getChannel().sendMessage(builder.toString()).queue();
    }
}
