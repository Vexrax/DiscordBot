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

    //HELP COMMAND
    public String COMMANDS_HELP = "Commands";
    public String COMMAND_HELP_SONG = "//song (play, get, clear, skip, banish)";
    public String COMMAND_HELP_PING = "//ping";
    public String COMMAND_HELP_ROLEDICE = "//roledice";
    public String COMMAND_HELP_FLIPCOIN = "//flipcoin";
    public String COMMAND_HELP_QUOTE = "//quote (add, remove, vote)";
    public String COMMAND_HELP_SUMMON = "//summon";
    public String COMMAND_HELP_POKEMONBATTLE = "//pokemonbattle (newbattle, switch, move)";
    public String COMMAND_HELP_EXAMPLE = "Example command '//song play warriors'";

    //SONG COMMAND
    public String COMMAND_SONG_PLAY = "play";
    public String COMMAND_SONG_SKIP = "skip";
    public String COMMAND_SONG_CLEAR = "clear";
    public String COMMAND_SONG_BANISH = "banish";
    public String COMMAND_SONG_GET = "get";
    public String COMMAND_SONG_PLAYLIST = "playlist";
    public String COMMAND_SONG_CURRENT = "current";
    public String COMMAND_SONG_HELP = "Song Help:\n //song play (song)\t\t\t-> play a song or playlist\n //song clear\t\t\t-> clear the queue\n //song get  (current/playlist)\t\t\t-> get the current playing song or the playlist\n //song banish\t\t\t-> make the bot leave your channel\n //song skip\t\t\t-> skip the current song\n";
    public String COMMAND_SONG_TEXT_JOINCHANNEL = "You must join a channel first";
    public String COMMAND_SONG_TEXT_NOTPLAYING = "Player is not currently playing song.";
    public String COMMAND_SONG_TEXT_SKIPPING = "Skipping current song";
    public String COMMAND_SONG_TEXT_PLAYLISTEMPTY = "Your playlist is already empty.";
    public String COMMAND_SONG_TEXT_CLEARPLAYLIST = "Playlist cleared.";
    public String COMMAND_SONG_TEXT_LEAVECHANNEL = "Leaving the channel.";
    public String COMMAND_SONG_TEXT_CURRENTPLAYLIST = "Current Playlist:\n";
    public String COMMAND_SONG_TEXT_CURRENTSONG = "Current Song:\n";




}
