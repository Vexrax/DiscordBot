package DiscordBot.Skynet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.security.auth.login.LoginException;


import Backend.BotListener;
import Backend.CommandParser;
import Backend.Util;
import Commands.*;
import Commands.QuoteCommand.QuoteCommand;
import Commands.SongCommand.SongCommand;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;


public class App 
{
	private static final String API_KEY_PATH = "APIKEYS.txt";

	public static final CommandParser parser = new CommandParser();
	
	public static HashMap<String, Command> commands = new HashMap<String, Command>();
	public static HashMap<String, String> APIkeys = new HashMap<String, String>();
	public static boolean serviceMode = false;
	public static Util util = new Util();
	
    public static void main(String[] args) throws LoginException, IllegalArgumentException, RateLimitedException, InterruptedException
    {

    	try
		{
			getApiKeys(); //get the api keys
			JDA bot;
			bot = new JDABuilder(AccountType.BOT)
					.setToken(APIkeys.get("DISCORD:"))
					.setGame(Game.of("Taking Over The World"))
					.buildBlocking();
			bot.addEventListener(new BotListener());
			addCommands();//SET TOKEN
		}
		catch(Exception e)
		{
			System.out.println("exception caught: " + e);
		}
    }
	private static void getApiKeys()
	{
		String path = API_KEY_PATH;
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

	}
    
    public static void handleCommand(CommandParser.CommandContainer cmd)
    {
    	if (serviceMode)
    	{
    		return;
    	}

    	if(commands.containsKey(cmd.invoke))
    	{
    		boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.e);
    		if(safe)
    		{
    			commands.get(cmd.invoke).action(cmd.args, cmd.e);
    			commands.get(cmd.invoke).executed(safe, cmd.e);
    		}
    		else 
    		{
    			commands.get(cmd.invoke).executed(safe, cmd.e);
    		}
    	}
    }

    private static void addCommands()
    {
        commands.put("ping", new PingCommand());
        commands.put("quote", new QuoteCommand());
        commands.put("song", new SongCommand(APIkeys.get("GOOGLE:")));
        commands.put("rolldice", new RollDiceCommand());
        commands.put("flipcoin", new FlipCoinCommand());
        commands.put("end", new EndBotCommand());
        commands.put("summoner", new SummonerCommand(APIkeys.get("RIOT:")));
        commands.put("summon", new SummonCommand());
        commands.put("", new HelpCommand());
		commands.put("help", new HelpCommand());
	}
}


