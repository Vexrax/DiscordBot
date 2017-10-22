package DiscordBot.Skynet;


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
        	callCommand(commandarray, objMsg, objChannel, objUser);
        }
    }
    public void quote(MessageChannel objChannel)
    {
    	//todo
    }
    
    public void quoteMe(String quote, MessageChannel objChannel)
    {
    	//todo
    }
    
    public void playSong(String songurl, MessageChannel objChannel)
    {
    	//todo
    }
    
    
    public void playlist(String playlisturl, MessageChannel objChannel)
    {
    	//todo
    }
    
    public void flipCoin(String calledside, MessageChannel objChannel, User objUser)
    {
    	if (!calledside.equals("heads") || !calledside.equals("tails"))
    	{
    		objChannel.sendMessage(calledside + " is not heads or tails bud, try again.");
    		return;
    	}
    	int Random = (int)(Math.random()*2);
    	if (Random == 1 && calledside.equals("heads") || Random == 0 && calledside.equals("tails"))
    	{
    		objChannel.sendMessage(objUser.getName() + " called the side correctly");
    		return;
    	}
    	objChannel.sendMessage(objUser.getName() + " called the side incorrectly");
    }
    
    
    public void callCommand(String[] commandarray, Message objMsg, MessageChannel objChannel, User objUser)
    {
    	if (commandarray[0].equalsIgnoreCase("//quote"))
    	{
    		quote(objChannel);
    	}
    	else if(commandarray[0].equalsIgnoreCase("//quoteme"))
    	{
    		try
    		{
    			quoteMe(commandarray[1], objChannel);
    		}
    		catch(Exception exception) //FIX EXCEPTION CONDTION
    		{
    			objChannel.sendMessage("No quote specified, try again");
    		}
    	}
    	else if(commandarray[0].equalsIgnoreCase("//play"))
    	{
    		try
    		{
    			playSong(commandarray[1], objChannel);
    		}
    		catch(Exception exception) //FIX EXCEPTION CONDTION
    		{
    			objChannel.sendMessage("No song specified, try again");
    		}
    	}
    	else if(commandarray[0].equalsIgnoreCase("//playlistYT"))
    	{
    		try
    		{
    			playlist(commandarray[1], objChannel);
    		}
    		catch(Exception exception) //FIX EXCEPTION CONDTION
    		{
    			objChannel.sendMessage("No youtube playlist specified, try again");
    		}
    	}
    	else if(commandarray[0].equalsIgnoreCase("//flipcoin"))
    	{
    		try
    		{
    			flipCoin(commandarray[1], objChannel, objUser);
    		}
    		catch(Exception exception) //FIX EXCEPTION CONDTION
    		{
    			objChannel.sendMessage("Please call heads or tails before flipping.");
    		}
    	}
    }
    
}

