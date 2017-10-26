package pokeAPI;

public class Normal 
{
	 private int use_before;

	    private Use_after[] use_after;

	    public int getUse_before ()
	    {
	        return use_before;
	    }

	    public void setUse_before (int use_before)
	    {
	        this.use_before = use_before;
	    }

	    public Use_after[] getUse_after ()
	    {
	        return use_after;
	    }

	    public void setUse_after (Use_after[] use_after)
	    {
	        this.use_after = use_after;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [use_before = "+use_before+", use_after = "+use_after+"]";
	    }
}
