package pokeAPI;

public class Contest_combos
{
	 private Super supers;

	    private Normal normal;

	    public Super getSuper ()
	    {
	        return supers;
	    }

	    public void setSuper (Super supers)
	    {
	        this.supers = supers;
	    }

	    public Normal getNormal ()
	    {
	        return normal;
	    }

	    public void setNormal (Normal normal)
	    {
	        this.normal = normal;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [super = "+supers+", normal = "+normal+"]";
	    }
}
