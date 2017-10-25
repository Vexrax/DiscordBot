package pokeAPI;

public class Abilities 
{
	 private String is_hidden;

	    private String slot;

	    private Ability ability;

	    public String getIs_hidden ()
	    {
	        return is_hidden;
	    }

	    public void setIs_hidden (String is_hidden)
	    {
	        this.is_hidden = is_hidden;
	    }

	    public String getSlot ()
	    {
	        return slot;
	    }

	    public void setSlot (String slot)
	    {
	        this.slot = slot;
	    }

	    public Ability getAbility ()
	    {
	        return ability;
	    }

	    public void setAbility (Ability ability)
	    {
	        this.ability = ability;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [is_hidden = "+is_hidden+", slot = "+slot+", ability = "+ability+"]";
	    }
}
