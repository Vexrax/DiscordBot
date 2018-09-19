package Commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpCommand implements Command
{
    private User objUser;
    private TextChannel objTextChannel;
    private Guild objGuild;

    public boolean called(String[] args, MessageReceivedEvent e)
    {
        objUser = e.getAuthor();
        objTextChannel = e.getTextChannel();
        objGuild = e.getGuild();
        return true;
    }
    public void action(String[] args, MessageReceivedEvent e)
    {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Commands");
        builder.appendDescription("//song (play, get, clear, skip, banish)\n");
        builder.appendDescription("//ping\n");
        builder.appendDescription("//roledice\n");
        builder.appendDescription("//flipcoin (heads, tails)\n");
        builder.appendDescription("//quote (add, remove, vote)\n");
        builder.appendDescription("//summon\n");
        builder.appendDescription("//pokemonbattle\n");
        builder.appendDescription("Example command '//song play warriors'");
        objTextChannel.sendMessage(builder.build()).queue();
    }
    public String help()
    {
        return null;
    }
    public void executed(boolean success, MessageReceivedEvent e)
    {
        return;
    }
}
