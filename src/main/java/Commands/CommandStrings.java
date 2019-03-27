package Commands;

public class CommandStrings
{
    private static CommandStrings ourInstance = new CommandStrings();

    public static CommandStrings getInstance()
    {
        return ourInstance;
    }

    private CommandStrings()
    {
    }

    public String RANK_NOT_HIGH_ENOUGH_TO_END = "This user is not a high enough rank to end me";
    public String ADMIN_OF_SKYNET = "Admin of skynet";
    public String ENDED_PROCCESS = "has ended the proccess";
    public String HEADS = "Heads";
    public String TAILS = "Tails";
    public String COIN_LANDED = "The Coin Landed on";
    public String COMMANDS_HELP = "Commands";
    public String COMMAND_HELP_SONG = "//song (play, get, clear, skip, banish)";
    public String COMMAND_HELP_PING = "//ping";
    public String COMMAND_HELP_ROLEDICE = "//roledice";
    public String COMMAND_HELP_FLIPCOIN = "//flipcoin";
    public String COMMAND_HELP_QUOTE = "//quote (add, remove, vote)";
    public String COMMAND_HELP_SUMMON = "//summon";
    public String COMMAND_HELP_POKEMONBATTLE = "//pokemonbattle (newbattle, switch, move)";
    public String COMMAND_HELP_EXAMPLE = "Example command '//song play warriors'";

}
