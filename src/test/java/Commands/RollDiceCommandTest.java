package Commands;

import BaseTest.BaseTestCase;
import DiscordBot.Skynet.App;
import junit.framework.Assert;
import junit.framework.TestCase;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RollDiceCommandTest extends BaseTestCase
{
    Command rollDiceCommand = commandFactory.getCommand("rolldice");
    @Override
    protected void setUp()
    {

    }

    public void  RollDiceLandsWithinTen(String[] args, MessageReceivedEvent e)
    {
        rollDiceCommand.action(args, e);
    }
}
