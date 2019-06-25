package BaseTest;

import Commands.CommandFactory;
import DiscordBot.Skynet.App;
import junit.framework.TestCase;

public class BaseTestCase extends TestCase
{
    public CommandFactory commandFactory;
    private App skynetInstance;
    public BaseTestCase()
    {
        skynetInstance = new App();
        commandFactory = new CommandFactory(App.getApiKeys());
    }

}
