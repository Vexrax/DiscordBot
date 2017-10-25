package pokeAPI;

public class Moves
{
	private Version_group_details[] version_group_details;

    private Move move;

    public Version_group_details[] getVersion_group_details ()
    {
        return version_group_details;
    }

    public void setVersion_group_details (Version_group_details[] version_group_details)
    {
        this.version_group_details = version_group_details;
    }

    public Move getMove ()
    {
        return move;
    }

    public void setMove (Move move)
    {
        this.move = move;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [version_group_details = "+version_group_details+", move = "+move+"]";
    }
}
