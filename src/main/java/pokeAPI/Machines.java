package pokeAPI;

public class Machines 
{
	private Version_group version_group;

    private Machine machine;

    public Version_group getVersion_group ()
    {
        return version_group;
    }

    public void setVersion_group (Version_group version_group)
    {
        this.version_group = version_group;
    }

    public Machine getMachine ()
    {
        return machine;
    }

    public void setMachine (Machine machine)
    {
        this.machine = machine;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [version_group = "+version_group+", machine = "+machine+"]";
    }
}
