package DiscordBot.Skynet.Commands.QuoteCommand;


import DiscordBot.Skynet.Backend.Util;
import DiscordBot.Skynet.Commands.Command;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static DiscordBot.Skynet.Commands.QuoteCommand.QuoteCommand.quoteFilePath;

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
        try
        {
            int numberoflines = util.getFileLineLength(quoteFilePath);
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
}
