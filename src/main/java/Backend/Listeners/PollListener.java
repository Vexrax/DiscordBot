package Backend.Listeners;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;


public class PollListener extends ListenerAdapter
{

    public ArrayList<String> ReactionMap = new ArrayList<String>();

    public PollListener()
    {
        //Just a Map to all the number emojis
        ReactionMap.add("1⃣");
        ReactionMap.add("2⃣");
        ReactionMap.add("3⃣");
        ReactionMap.add("4⃣");
        ReactionMap.add("5⃣");
        ReactionMap.add("6⃣");
        ReactionMap.add("7⃣");
        ReactionMap.add("8⃣");
        ReactionMap.add("9⃣");
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        if(MessageIsAPoll(event.getMessage()))
        {
            AddReactionOptions(event.getMessage().getEmbeds().get(0), event.getMessage());
        }
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        String s = event.getReaction().getEmote().toString();
    }

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
            message.addReaction(ReactionMap.get(i)).queue();
        }
    }


}
