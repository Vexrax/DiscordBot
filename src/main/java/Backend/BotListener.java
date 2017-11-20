package Backend;

import java.awt.Event;

import org.apache.http.impl.execchain.MainClientExec;

import DiscordBot.Skynet.App;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter
{
	@Override 
    public void onMessageReceived(MessageReceivedEvent e)
	{
		if(e.getMessage().getContent().startsWith("//") && e.getMessage().getAuthor().getId() != e.getJDA().getSelfUser().getId())
			App.handleCommand(App.parser.parse(e.getMessage().getContent(), e));
	}
	@Override
	public void onReady(ReadyEvent e)
	{
		//App.log("Status", "Logged in as: " + e.getJDA().getSelfUser().getId());
	}
}
