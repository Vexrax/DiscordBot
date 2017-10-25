package pokeAPI;

public class Game_indices 
{
    private String game_index;

    private Version version;

    public String getGame_index ()
    {
        return game_index;
    }

    public void setGame_index (String game_index)
    {
        this.game_index = game_index;
    }

    public Version getVersion ()
    {
        return version;
    }

    public void setVersion (Version version)
    {
        this.version = version;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [game_index = "+game_index+", version = "+version+"]";
    }
}
