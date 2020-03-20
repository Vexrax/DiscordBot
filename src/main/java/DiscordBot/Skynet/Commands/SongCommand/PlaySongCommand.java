package DiscordBot.Skynet.Commands.SongCommand;


import DiscordBot.Skynet.Backend.Search;
import DiscordBot.Skynet.Backend.Util;
import DiscordBot.Skynet.Commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class PlaySongCommand implements Command
{
    private String API_KEY;
    Util util = new Util();
    public PlaySongCommand(String API)
    {
        this.API_KEY = API;
    }
    public boolean called(String[] args, MessageReceivedEvent e)
    {
        return args[0].startsWith("https://www.youtube.com/watch?v=") || args[0].length() > 0;
    }

    public void action(String[] args, MessageReceivedEvent e)
    {
        if(!args[0].startsWith("https://www.youtube.com/watch?v="))
        {
            Search url = new Search();
            String urlToPlay = "https://www.youtube.com/watch?v=" + url.searchToUrl(util.convertArgListToSingleString(args, 0) + "lyrics", this.API_KEY);
            MusicCommands.getInstance().play(urlToPlay, e);
        }
        else
        {
            MusicCommands.getInstance().play(args[0], e);
        }
    }



    public String help() {
        return null;
    }

    public void executed(boolean success, MessageReceivedEvent e)
    {
    }
}
