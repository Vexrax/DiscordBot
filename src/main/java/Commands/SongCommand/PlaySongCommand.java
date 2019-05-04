package Commands.SongCommand;

import Backend.Search;
import Commands.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class PlaySongCommand implements Command
{
    private String API_KEY;
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
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < args.length-1; i++)
            {
                builder.append(args[i] + " ");
            }
            Search url = new Search();
            String urlToPlay = "https://www.youtube.com/watch?v=" + url.searchToUrl(builder.toString() + "lyrics", this.API_KEY);
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
