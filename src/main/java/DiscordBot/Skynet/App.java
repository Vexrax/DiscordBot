package DiscordBot.Skynet;

import java.lang.reflect.Array;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class App extends ListenerAdapter
{
    public static void main( String[] args ) throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException
    {
        JDA bot = new JDABuilder(AccountType.BOT).setToken("").buildBlocking(); //SET TOKEN
    }
    
    @Override 
    public void onMessageReceived(MessageReceivedEvent e)
    {
        Message objMsg = e.getMessage();
        MessageChannel objChannel = e.getChannel();
        User objUser = e.getAuthor();   
        String commandarray[] = objMsg.getContent().split(" ", 2);
        if (objMsg.getContent().substring(0,2) == "//")
        {
        	if (commandarray[0].equalsIgnoreCase("//quote"))
        	{
        		quote();
        	}
        	if(commandarray[0].equalsIgnoreCase("//quoteme"))
        	{
        		try
        		{
        			quoteMe(commandarray[0]);
        		}
        		catch(Exception exception) //FIX EXCEPTION CONDTION
        		{
        			objChannel.sendMessage("No quote specified, try again");
        		}
        	}
        		
        }
    }
    public void quote()
    {
    	//todo
    }
    public void quoteMe(String quote)
    {
    	//todo
    }
}

