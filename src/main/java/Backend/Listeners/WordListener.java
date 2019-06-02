package Backend.Listeners;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class WordListener extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent e)
    {
        parseMessage(e.getMessage().getContent());
    }

    @Override
    public void onReady(ReadyEvent e)
    {

    }

    public void parseMessage(String message)
    {
        String[] arrOfWords = message.split(" ");
        for(String word : arrOfWords)
        {
            addWordToDataBase(word);
        }
    }

    public void addWordToDataBase(String word)
    {

    }
}
