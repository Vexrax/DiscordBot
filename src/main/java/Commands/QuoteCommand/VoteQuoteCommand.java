package Commands.QuoteCommand;

import Backend.Ranks;
import Backend.Util;
import Commands.Command;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


import static Commands.QuoteCommand.QuoteCommand.*;


public class VoteQuoteCommand implements Command
{
    Util util = new Util();

    public boolean called(String[] args, MessageReceivedEvent e)
    {
        return args.length == 0 || args.length == 1;
    }

    public void action(String[] args, MessageReceivedEvent e)
    {
        if(args.length == 0)
        {
            Vote(e, false);
            return;
        }
        Vote(e, args[0].equalsIgnoreCase("force"));
    }

    public String help() {
        return null;
    }

    public void executed(boolean success, MessageReceivedEvent e) {

    }

    private void Vote(MessageReceivedEvent e, boolean force)
    {
        final MessageChannel objChannel = e.getChannel();
        if(force && util.getUserRank(e.getAuthor().getId()) != Ranks.Admin)
        {
            objChannel.sendMessage("You cannot force the vote to pass.").queue();
        }
        if(quoteBeingAddedOrRemoved)
        {
            if(!votedList.contains(e.getAuthor().getId()))
            {
                addVote(e, force, objChannel);
                return;
            }
            objChannel.sendMessage("You have already voted").queue();
            return;
        }
        objChannel.sendMessage("No vote currently being conducted").queue();

    }

    private void addVote(MessageReceivedEvent e, boolean force, MessageChannel objChannel)
    {
        votedList.add(e.getAuthor().getId());
        if((util.getUserRank(e.getAuthor().getId()) == Ranks.Admin) && force)
        {
            objChannel.sendMessage(e.getAuthor().getName() + " is forcing the vote to pass").queue();
            currentVotes += requiredVotes + 1;
            objChannel.sendMessage("Current Votes: " + currentVotes + " Required Votes: " + requiredVotes).queue();
        }
        else
        {
            currentVotes += 1;
            SendVoteBeingAddedToTally(objChannel);
        }
    }

    private void SendVoteBeingAddedToTally(MessageChannel objChannel)
    {
        objChannel.sendMessage("Your vote has been added").queue();
        objChannel.sendMessage("Current Votes: " + currentVotes + " Required Votes: " + requiredVotes).queue();
    }
}
