package Commands;

import com.sun.prism.paint.Color;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class PollCommand implements Command
{
    boolean pollStarted = false;
    protected static int voteTime = 120000;

    public boolean called(String[] args, MessageReceivedEvent e)
    {
        return args.length >= 2;
    }

    public void action(String[] args, MessageReceivedEvent e)
    {
        if(isPollInProgress(e))
        {
            return;
        }
        pollStarted = true;
        e.getTextChannel().sendMessage("A poll has been started by "  + e.getAuthor().getName() + ". Use the reactions to vote on your choice.").queue();
        SetupPollTimer(e);
        SendPollMessage(args, e);
    }

    private void SendPollMessage(String[] args, MessageReceivedEvent e)
    {
        TextChannel objTextChannel = e.getTextChannel();
        EmbedBuilder embededMessageBuilder = new EmbedBuilder();
        embededMessageBuilder.setTitle("Poll");
        int i =  0;
        for(String polloption : args)
        {
            embededMessageBuilder.appendDescription(i+1 + ". " + polloption + "\n");
            i++;
        }
        objTextChannel.sendMessage(embededMessageBuilder.build()).queue();
    }

    private void SetupPollTimer(final MessageReceivedEvent e) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run()
            {
                CalculatePollWinners(e);
                pollStarted = false;
            }}, voteTime);
    }

    public String help()
    {
        return null;
    }

    public void executed(boolean success, MessageReceivedEvent e)
    {

    }

    private boolean isPollInProgress(MessageReceivedEvent e)
    {
        if(pollStarted)
        {
            e.getTextChannel().sendMessage("A poll is already in progress, please wait for the current poll to finish.").queue();
        }
        return pollStarted;
    }

    private void  CalculatePollWinners(MessageReceivedEvent e)
    {
        e.getTextChannel().sendMessage(String.format("The Option '%s' Won the poll"));
    }
}
