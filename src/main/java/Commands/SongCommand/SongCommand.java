package Commands.SongCommand;
import java.util.HashMap;

import Backend.Util;
import Commands.Command;
import Commands.CommandStrings;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SongCommand implements Command
{
	public static HashMap<String, Command> commands = new HashMap<String, Command>();
	private TextChannel objTextChannel;

	public SongCommand(String YoutubeApiKey)
	{
		commands.put(CommandStrings.getInstance().COMMAND_SONG_PLAY, new PlaySongCommand(YoutubeApiKey));
		commands.put(CommandStrings.getInstance().COMMAND_SONG_BANISH, new BanishCommand());
		commands.put(CommandStrings.getInstance().COMMAND_SONG_GET, new GetSongCommand());
		commands.put(CommandStrings.getInstance().COMMAND_SONG_CLEAR, new ClearSongCommand());
		commands.put(CommandStrings.getInstance().COMMAND_SONG_SKIP, new SkipSongCommand());
	}
	public boolean called(String[] args, MessageReceivedEvent e) 
	{
		objTextChannel = e.getTextChannel();
        if(args.length == 0)
        {
			objTextChannel.sendMessage(help()).queue();
			return false;
		}
		return commands.containsKey(args[0]);
	}

	public void action(String[] args, MessageReceivedEvent e)
	{
		String[] cleanedArgs = Util.cleanArgs(args);
		boolean safe = commands.get(args[0]).called(cleanedArgs, e);
		if(safe)
		{
			commands.get(args[0]).action(cleanedArgs, e);
			commands.get(args[0]).executed(safe, e);
		}
		else
		{
			commands.get(args[0]).executed(safe, e);
		}
	}


	public String help() {
		return CommandStrings.getInstance().COMMAND_SONG_HELP;
	}

	public void executed(boolean success, MessageReceivedEvent event)
	{
	}

}
