package DiscordBot.Skynet.Backend.Listeners;

import DiscordBot.Skynet.Backend.Util;
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
            addReactionOptions(event.getMessage().getEmbeds().get(0), event.getMessage());
        }
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event)
    {
        Message message = event.getChannel().getMessageById(event.getMessageId()).complete();
        if(MessageIsAPoll(message) && util.isStringValidUnicodeEmoji(event.getReaction().getEmote().getName()))
        {
            votes[util.convertUnicodeStringToInt(event.getReaction().getEmote().getName())-1] += 1;
        }
    }

    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event)
    {
        Message message = event.getChannel().getMessageById(event.getMessageId()).complete();
        if(MessageIsAPoll(message) && util.isStringValidUnicodeEmoji(event.getReaction().getEmote().getName()))
        {
            votes[util.convertUnicodeStringToInt(event.getReaction().getEmote().getName())-1] -= 1;
        }
    }

    private boolean MessageIsAPoll(Message message)
    {
        if(message.getEmbeds().size() > 0)
        {
            return message.getAuthor().isBot() && message.getEmbeds().get(0).getTitle().equals("[Poll Active]");
        }
        return false;
    }

    private void addReactionOptions(MessageEmbed messageEmbed, Message message)
    {
        String[] args = messageEmbed.getDescription().split("\n");
        for(int i = 0; i < args.length; i++)
        {
            message.addReaction(util.convertIntegerToUnicodePollString(i+1)).queue();
        }
    }

    public static int[] getListOfVotes()
    {
        return votes;
    }

    public static void clearVotes()
    {
        votes = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }

}
