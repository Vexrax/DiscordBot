package Backend.Listeners;

import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageEmbedEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageEmbedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;

public class PollListener extends ListenerAdapter
{

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        if(event.getAuthor().isBot())
        {
            event.getMessage().addReaction("1⃣").queue();
        }

    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        String s = event.getReaction().getEmote().toString();
    }

    private MessageEmbed GetEmbedPollMessage(List<MessageEmbed> listOfEmdedMessages, String title)
    {
        for(MessageEmbed embedMessage : listOfEmdedMessages)
        {
            if(embedMessage.getTitle() == title)
            {
                return embedMessage;
            }
        }
        return null;
    }
}
