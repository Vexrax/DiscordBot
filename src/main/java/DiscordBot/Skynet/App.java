package DiscordBot.Skynet;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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
        JDA bot = new JDABuilder(AccountType.BOT).setToken(inputToken()).buildBlocking(); //SET TOKEN
        bot.addEventListener(new App());
    }
    
    @Override 
    public void onMessageReceived(MessageReceivedEvent e)
    {
        Message objMsg = e.getMessage();
        MessageChannel objChannel = e.getChannel();
        User objUser = e.getAuthor();   
        String commandarray[] = objMsg.getContent().split(" ", 2);
        if (objMsg.getContent().substring(0,2).equals("//"))
        {
        	callCommand(commandarray, objMsg, objChannel, objUser);
        }
    }
    private void quote(MessageChannel objChannel) 
    {
    	String path = "quoteFile.txt";
    	try
    	{
    		BufferedReader textreader = new BufferedReader(new FileReader(path));
    		int numberoflines = 0;
    		while(textreader.readLine() != null)
    		{
    			numberoflines ++;
    		}
    		textreader.close();
    		String[] quotearray = new String[numberoflines]; 
    		BufferedReader textreaderforquotes = new BufferedReader(new FileReader(path));
    		for(int i = 0; i < numberoflines; i++)
    		{
    			quotearray[i] = textreaderforquotes.readLine();
    		}
    		objChannel.sendMessage(quotearray[(int)(Math.random()*numberoflines)]).queue();
    		textreaderforquotes.close();
    		
    	}  	
    	catch(IOException e) 
    	{
    		objChannel.sendMessage("Somethng went wrong, missing quotefile string").queue();
    	}

    	
    }
    
    private void quoteMe(String quote, MessageChannel objChannel)
    {
    	//todo
    }
    
    private void playSong(String songurl, MessageChannel objChannel)
    {
    	//todo
    }
    
    
    private void playlist(String playlisturl, MessageChannel objChannel)
    {
    	//todo
    }
    
    private void flipCoin(String calledside, MessageChannel objChannel, User objUser)
    {
    	if (calledside.equals("heads") || calledside.equals("tails"))
    	{
    		int Random = (int)(Math.random()*2);
        	if (Random == 1 && calledside.equals("heads") || Random == 0 && calledside.equals("tails"))
        	{
        		objChannel.sendMessage(objUser.getName() + " called the side CORRECTLY").queue();
        		return;
        	}
        	objChannel.sendMessage(objUser.getName() + " called the side INCORRECTLY").queue();
    	}
		objChannel.sendMessage(calledside + " is not heads or tails bud, try again.").queue();
    	
    }
    
    private static String inputToken()
    {
    	System.out.println("Enter your bot token: ");
    	Scanner scanner = new Scanner(System.in);
    	return scanner.nextLine();
    }
    
    
    private void callCommand(String[] commandarray, Message objMsg, MessageChannel objChannel, User objUser)
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
    			objChannel.sendMessage("No quote specified, try again").queue();
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
    			objChannel.sendMessage("No song specified, try again").queue();
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
    			objChannel.sendMessage("No youtube playlist specified, try again").queue();
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
    			objChannel.sendMessage("Please call heads or tails before flipping.").queue();
    		}
    	}
    }
    
}

