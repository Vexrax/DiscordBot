package pokeAPI;

public class Super 
{
	 	private int use_before;

	    private int use_after;

	    public int getUse_before ()
	    {
	        return use_before;
	    }

	    public void setUse_before (int use_before)
	    {
	        this.use_before = use_before;
	    }

	    public int getUse_after ()
	    {
	        return use_after;
	    }

	    public void setUse_after (int use_after)
	    {
	        this.use_after = use_after;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [use_before = "+use_before+", use_after = "+use_after+"]";
	    }
}
