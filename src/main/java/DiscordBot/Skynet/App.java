package DiscordBot.Skynet;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.Channel;
import java.util.HashMap;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import org.omg.CORBA.SystemException;

import Backend.BotListener;
import Backend.CommandParser;
import Commands.Command;
import Commands.FlipCoinCommand;
import Commands.PingCommand;
import Commands.PlaySongCommand;
import Commands.PokemonBattleCommand;
import Commands.QuoteCommand;
import Commands.RollDiceCommand;
import music.MusicManager;
import music.MusicPlayer;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.entities.impl.MessageEmbedImpl;
import net.dv8tion.jda.core.events.message.MessageEmbedEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import pokemon.BattleModel;

public class App 
{
	private static final char[][] CommandArray = null;
	public static final CommandParser parser = new CommandParser();
	
	public static HashMap<String, Command> commands = new HashMap<String, Command>();
	
    public static void main( String[] args ) throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException
    {
        JDA bot = new JDABuilder(AccountType.BOT).setToken(inputToken()).buildBlocking(); //SET TOKEN
        bot.addEventListener(new BotListener());
        commands.put("ping", new PingCommand());
        commands.put("quote", new QuoteCommand());
        commands.put("song", new PlaySongCommand());
        commands.put("rolldice", new RollDiceCommand());
        commands.put("flipcoin", new FlipCoinCommand());
        commands.put("pokemonbattle", new PokemonBattleCommand());

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
    private static String inputToken()
    {
    	System.out.println("Enter your bot token: ");
    	Scanner scanner = new Scanner(System.in);
    	return scanner.nextLine();
    }    
}


