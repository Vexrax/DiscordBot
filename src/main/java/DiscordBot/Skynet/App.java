package DiscordBot.Skynet;

import DiscordBot.Skynet.Backend.CommandParser;
import DiscordBot.Skynet.Backend.Listeners.CommandListener;
import DiscordBot.Skynet.Backend.Listeners.PollListener;
import DiscordBot.Skynet.Backend.Listeners.WordListener;
import DiscordBot.Skynet.Backend.Util;
import DiscordBot.Skynet.Commands.CommandFactory;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String API_KEY_PATH = "APIKEYS.txt";

    public static final CommandParser parser = new CommandParser();

    public static CommandFactory commandFactory;
    public static boolean serviceMode = false;
    public static Util util = new Util();
    public static void main( String[] args )
    {
        try
        {
            HashMap<String, String> APIkeys = getApiKeys(); //get the api keys
            commandFactory = new CommandFactory(APIkeys);
            JDA bot = new JDABuilder(AccountType.BOT)
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
            e.printStackTrace();
        }
    }


    public static HashMap<String, String> getApiKeys()
    {
        HashMap<String, String> APIkeys = new HashMap<String, String>();
        APIkeys.put("RIOT:", System.getenv("RIOT"));
        APIkeys.put("GOOGLE:", System.getenv("GOOGLE"));
        APIkeys.put("DISCORD:", System.getenv("DISCORD"));
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
