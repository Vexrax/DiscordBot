package DiscordBot.Skynet.Commands;



import DiscordBot.Skynet.Commands.QuoteCommand.QuoteCommand;
import DiscordBot.Skynet.Commands.SongCommand.SongCommand;

import java.util.HashMap;

public class CommandFactory
{
    public static HashMap<String, Command> commands = new HashMap<String, Command>();

    public CommandFactory(HashMap<String, String> APIkeys)
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
        commands.put("poll", new PollCommand());

    }

    public Command getCommand(String commandName)
    {
        return commands.get(commandName);
    }

    public Boolean containsCommand(String commandName)
    {
        return commands.containsKey(commandName);
    }
}
