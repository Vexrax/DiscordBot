package pokeAPI;

public class Version_group_details 
{
	private Move_learn_method move_learn_method;

    private Version_group version_group;

    private String level_learned_at;

    public Move_learn_method getMove_learn_method ()
    {
        return move_learn_method;
    }

    public void setMove_learn_method (Move_learn_method move_learn_method)
    {
        this.move_learn_method = move_learn_method;
    }

    public Version_group getVersion_group ()
    {
        return version_group;
    }

    public void setVersion_group (Version_group version_group)
    {
        this.version_group = version_group;
    }

    public String getLevel_learned_at ()
    {
        return level_learned_at;
    }

    public void setLevel_learned_at (String level_learned_at)
    {
        this.level_learned_at = level_learned_at;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [move_learn_method = "+move_learn_method+", version_group = "+version_group+", level_learned_at = "+level_learned_at+"]";
    }
}
