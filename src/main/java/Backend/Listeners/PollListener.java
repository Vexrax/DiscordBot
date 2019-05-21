package Backend.Listeners;

import Backend.Util;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class PollListener extends ListenerAdapter
{

    private static int[] votes = {0, 0, 0, 0, 0, 0, 0, 0, 0 };
    Util util = new Util();

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        if(MessageIsAPoll(event.getMessage()))
        {
            AddReactionOptions(event.getMessage().getEmbeds().get(0), event.getMessage());
        }
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event)
    {
        Message message = event.getChannel().getMessageById(event.getMessageId()).complete();
        if(MessageIsAPoll(message) && util.IsStringValidUnicodeEmoji(event.getReaction().getEmote().getName()))
        {
            votes[util.ConvertUnicodeStringToInt(event.getReaction().getEmote().getName())-1] += 1;
        }
    }

    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event)
    {
        Message message = event.getChannel().getMessageById(event.getMessageId()).complete();
        if(MessageIsAPoll(message) && util.IsStringValidUnicodeEmoji(event.getReaction().getEmote().getName()))
        {
            votes[util.ConvertUnicodeStringToInt(event.getReaction().getEmote().getName())-1] -= 1;
        }
    }

    //TODO: Make it so that you can only vote if its an active POLL;
    private boolean MessageIsAPoll(Message message)
    {
        if(message.getEmbeds().size() > 0)
        {
            return message.getAuthor().isBot() && message.getEmbeds().get(0).getTitle().equals("Poll");
        }
        return false;
    }

    private void AddReactionOptions(MessageEmbed messageEmbed, Message message)
    {
        String[] args = messageEmbed.getDescription().split("\n");
        for(int i = 0; i < args.length; i++)
        {
            message.addReaction(util.ConvertIntegerToUnicodePollString(i+1)).queue();
        }
    }

    public static int[] GetListOfVotes()
    {
        return votes;
    }

    public static void ClearVotes()
    {
        votes = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }

}
