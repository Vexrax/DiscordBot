package Commands;

import Backend.Listeners.PollListener;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PollCommand implements Command
{
    private boolean pollStarted = false;
    private static int voteTime = 120000;
    private Message CurrentPollMessage;

    public boolean called(String[] args, MessageReceivedEvent e)
    {
        int optionsCount = 0;
        for(String s : args)
        {
            if(s.contains("|"))
            {
                optionsCount += 1;
            }
        }
        if(!(optionsCount >= 1 && args.length >= 3))
        {
            e.getTextChannel().sendMessage("Invalid input. Example //poll Option A | Option B | Option C").queue();
        }
        return optionsCount >= 1 && args.length >= 3;
    }

    public void action(String[] args, MessageReceivedEvent e)
    {
        if(isPollInProgress(e))
        {
            return;
        }
        pollStarted = true;
        e.getTextChannel().sendMessage("A poll has been started by "  + e.getAuthor().getName() + ". Use the reactions to vote on your choice.").queue();
        String[] pollOptions = getPollOptions(args);
        SetupPollTimer(pollOptions, e);
        SendPollMessage(pollOptions, e);
    }

    public String help()
    {
        return "Please include atleast two options seperated by ' | ' Example Usage //Poll Option A | Option B | Option C";
    }

    public void executed(boolean success, MessageReceivedEvent e)
    {

    }

    private void SendPollMessage(String[] args, MessageReceivedEvent e)
    {
        TextChannel objTextChannel = e.getTextChannel();
        EmbedBuilder embededMessageBuilder = getDefaultPollEmbedBuilder(args);
        CurrentPollMessage = objTextChannel.sendMessage(embededMessageBuilder.build()).complete();
    }

    private EmbedBuilder getDefaultPollEmbedBuilder(String[] args) {
        EmbedBuilder embededMessageBuilder = new EmbedBuilder();
        embededMessageBuilder.setTitle("[Poll Active]");
        int i =  0;
        for(String polloption : args)
        {
            embededMessageBuilder.appendDescription(i+1 + ". " + polloption + "\n");
            i++;

        }
        return embededMessageBuilder;
    }

    private String[] getPollOptions(String[] args)
    {
        ArrayList<String> returningargs = new ArrayList<String>();
        StringBuilder currentString = new StringBuilder();
        for(String partialarg : args)
        {
            if(partialarg.endsWith("|"))
            {
                returningargs.add(currentString.toString());
                currentString = new StringBuilder();
                continue;
            }
            currentString.append(partialarg + " ");
        }
        returningargs.add(currentString.toString());
        return returningargs.toArray(new String[returningargs.size()]);
    }

    private void SetupPollTimer(final String[] args, final MessageReceivedEvent e)
    {
        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                CalculatePollWinners(args, e);
                SetPollAsInactive(args);
                pollStarted = false;
            }}, voteTime);
    }

    private void SetPollAsInactive(String[] args)
    {
        EmbedBuilder embedBuilder = getDefaultPollEmbedBuilder(args);
        embedBuilder.setTitle("[Poll Finished]");
        CurrentPollMessage.editMessage(embedBuilder.build()).queue();
    }

    private boolean isPollInProgress(MessageReceivedEvent e)
    {
        if(pollStarted)
        {
            e.getTextChannel().sendMessage("A poll is already in progress, please wait for the current poll to finish.").queue();
        }
        return pollStarted;
    }

    private void  CalculatePollWinners(String[] args, MessageReceivedEvent e)
    {
        int[] votes = PollListener.GetListOfVotes();
        ArrayList<Integer> winners = TallyVotes(votes);
        if (winners.size() > 1)
        {
            StringBuilder TiedStringBuilder = new StringBuilder();
            TiedStringBuilder.append("There was a Tie Between Options:\n");
            for(int winnerIndex : winners)
            {
                TiedStringBuilder.append(args[winnerIndex] + "\n");
            }
            e.getTextChannel().sendMessage(TiedStringBuilder.toString()).queue();
            PollListener.ClearVotes();
            return;
        }
        e.getTextChannel().sendMessage(String.format("The Option '%s' Won the poll", args[winners.get(0)])).queue();
        PollListener.ClearVotes();
    }

    private ArrayList<Integer> TallyVotes(int[] votes)
    {
        ArrayList<Integer> winners = new ArrayList<Integer>();
        int winningValue = 0;
        for (int i = 0; i < votes.length; i++)
        {
            if (votes[i] > winningValue)
            {
                winners = new ArrayList<Integer>();
                winningValue = votes[i];
                winners.add(i);
            }
            else if(votes[i] == winningValue)
            {
                winners.add(i);
            }
        }
        return winners;
    }
}
