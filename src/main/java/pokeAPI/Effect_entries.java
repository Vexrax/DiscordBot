package pokeAPI;

public class Effect_entries 
{
	 private String effect;

	    private Language language;

	    private String short_effect;

	    public String getEffect ()
	    {
	        return effect;
	    }

	    public void setEffect (String effect)
	    {
	        this.effect = effect;
	    }

	    public Language getLanguage ()
	    {
	        return language;
	    }

	    public void setLanguage (Language language)
	    {
	        this.language = language;
	    }

	    public String getShort_effect ()
	    {
	        return short_effect;
	    }

	    public void setShort_effect (String short_effect)
	    {
	        this.short_effect = short_effect;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [effect = "+effect+", language = "+language+", short_effect = "+short_effect+"]";
	    }
}
