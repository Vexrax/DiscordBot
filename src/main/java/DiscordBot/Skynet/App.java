package DiscordBot.Skynet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.security.auth.login.LoginException;


import Backend.Listeners.CommandListener;
import Backend.CommandParser;
import Backend.Listeners.PollListener;
import Backend.Listeners.WordListener;
import Backend.Util;
import Commands.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;


public class App 
{
	private static final String API_KEY_PATH = "APIKEYS.txt";

	public static final CommandParser parser = new CommandParser();

	public static CommandFactory commandFactory;
	public static boolean serviceMode = false;
	public static Util util = new Util();
	
    public static void main(String[] args) throws LoginException, IllegalArgumentException, RateLimitedException, InterruptedException
    {
    	try
		{
			HashMap<String, String> APIkeys = getApiKeys(); //get the api keys
			commandFactory = new CommandFactory(APIkeys);
			JDA bot;
			bot = new JDABuilder(AccountType.BOT)
					.setToken(APIkeys.get("DISCORD:"))
					.setGame(Game.of("Taking Over The World"))
					.buildBlocking();
			bot.addEventListener(new CommandListener());
			bot.addEventListener(new WordListener());
			bot.addEventListener(new PollListener());

		}
		catch(Exception e)
		{
			System.out.println("exception caught: " + e);
		}
    }

	public static HashMap<String, String> getApiKeys()
	{
		String path = API_KEY_PATH;
		HashMap<String, String> APIkeys = new HashMap<String, String>();
		try
		{
			BufferedReader textreader = new BufferedReader(new FileReader(path));
			int numberoflines = util.getFileLineLength(API_KEY_PATH);
			textreader.close();
			String[] quotearray = new String[numberoflines];
			BufferedReader textReaderForQuotes = new BufferedReader(new FileReader(path));
			for(int i = 0; i < numberoflines; i++)
			{
				quotearray[i] = textReaderForQuotes.readLine();
				String[] split = quotearray[i].split(" ");
				APIkeys.put(split[0], split[1]);
			}
			textReaderForQuotes.close();
		}

		catch(IOException exception)
		{
			System.out.println("File Could Not Be Found");
		}
		return APIkeys;
	}
    
    public static void handleCommand(CommandParser.CommandContainer cmd)
    {
    	if (serviceMode)
    	{
    		return;
    	}

    	if(commandFactory.containsCommand(cmd.invoke))
    	{
    		boolean safe = commandFactory.getCommand(cmd.invoke).called(cmd.args, cmd.e);
    		if(safe)
    		{
				commandFactory.getCommand(cmd.invoke).action(cmd.args, cmd.e);
				commandFactory.getCommand(cmd.invoke).executed(safe, cmd.e);
    		}
    		else 
    		{
				commandFactory.getCommand(cmd.invoke).executed(safe, cmd.e);
    		}
    	}
    }

}


