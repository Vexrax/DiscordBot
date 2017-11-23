package DiscordBot.Skynet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.security.auth.login.LoginException;


import Backend.BotListener;
import Backend.CommandParser;
import Commands.ChatBotTensorFlowCommand;
import Commands.Command;
import Commands.EndBotCommand;
import Commands.FlipCoinCommand;
import Commands.PingCommand;
import Commands.PlaySongCommand;
import Commands.PokemonBattleCommand;
import Commands.QuoteCommand;
import Commands.RollDiceCommand;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;


public class App 
{
	private static final String API_KEY_PATH = "APIKEYS.txt";

	public static final CommandParser parser = new CommandParser();
	
	public static HashMap<String, Command> commands = new HashMap<String, Command>();
	public static HashMap<String, String> APIkeys = new HashMap<String, String>();
	
    public static void main( String[] args ) throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException
    {
    	getApiKeys();
        JDA bot = new JDABuilder(AccountType.BOT).setToken(APIkeys.get("DISCORD:")).buildBlocking(); //SET TOKEN
        bot.addEventListener(new BotListener());
        addCommands();
    }
    
    public static void handleCommand(CommandParser.CommandContainer cmd)
    {
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
    private static void getApiKeys()
    {
		String path = API_KEY_PATH;
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
        		String[] split = quotearray[i].split(" ");
    			APIkeys.put(split[0], split[1]);
    		}
    		textreaderforquotes.close();
    	}  	
    	
    	catch(IOException exception) 
    	{
    		System.out.println("File Could Not Be Found");
    	}
    	
    }
    private static void addCommands()
    {
        commands.put("ping", new PingCommand());
        commands.put("quote", new QuoteCommand());
        commands.put("song", new PlaySongCommand(APIkeys.get("GOOGLE:")));
        commands.put("rolldice", new RollDiceCommand());
        commands.put("flipcoin", new FlipCoinCommand());
        commands.put("pokemonbattle", new PokemonBattleCommand());
        commands.put("chat", new ChatBotTensorFlowCommand());
        commands.put("end", new EndBotCommand());
    }
}


