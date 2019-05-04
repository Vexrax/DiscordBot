package Commands.QuoteCommand;

import Backend.Ranks;
import Backend.Util;
import Commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.*;

import static Commands.QuoteCommand.QuoteCommand.quoteFilePath;


public class ListQuoteCommand implements Command {
    Util util = new Util();

    public boolean called(String[] args, MessageReceivedEvent e) {
        return util.getUserRank(e.getAuthor().getId()) == Ranks.Admin;
    }

    public void action(String[] args, MessageReceivedEvent e)
    {
        try {
            int numberoflines = util.getFileLineLength(quoteFilePath);
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("List Of Quotes");
            embedBuilder.setColor(Color.BLACK);
            BufferedReader textreaderforquotes = new BufferedReader(new FileReader(quoteFilePath));
            for(int i = 0; i < numberoflines; i++)
            {
                embedBuilder.appendDescription(textreaderforquotes.readLine() + "\r\n");
            }
            textreaderforquotes.close();
            e.getChannel().sendMessage(embedBuilder.build()).queue();;
        }
        catch (Exception exception)
        {
            e.getChannel().sendMessage(exception.getMessage()).queue();
        }

    }

    public String help() {
        return "You must be a Skynet Admin to use this command";
    }

    public void executed(boolean success, MessageReceivedEvent e) {

    }
}
