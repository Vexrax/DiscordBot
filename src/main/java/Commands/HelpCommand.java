package Commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpCommand implements Command
{
    private TextChannel objTextChannel;

    public boolean called(String[] args, MessageReceivedEvent e)
    {
        objTextChannel = e.getTextChannel();
        return true;
    }
    public void action(String[] args, MessageReceivedEvent e)
    {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(CommandStrings.getInstance().COMMANDS_HELP);
        builder.appendDescription(String.format("%s \n", CommandStrings.getInstance().COMMAND_HELP_SONG));
        builder.appendDescription(String.format("%s \n", CommandStrings.getInstance().COMMAND_HELP_QUOTE));
        builder.appendDescription(String.format("%s \n", CommandStrings.getInstance().COMMAND_HELP_ROLEDICE));
        builder.appendDescription(String.format("%s \n", CommandStrings.getInstance().COMMAND_HELP_FLIPCOIN));
        builder.appendDescription(String.format("%s \n", CommandStrings.getInstance().COMMAND_HELP_PING));
        builder.appendDescription(String.format("%s \n", CommandStrings.getInstance().COMMAND_HELP_SUMMON));
        builder.appendDescription(String.format("%s \n", CommandStrings.getInstance().COMMAND_POLL_EXAMPLE));
        builder.appendDescription(String.format("%s \n", CommandStrings.getInstance().COMMAND_HELP_EXAMPLE));
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
