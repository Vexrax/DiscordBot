package pokeAPI;

public class Meta 
{
	 private Category category;

	    private String stat_chance;

	    private String flinch_chance;

	    private String ailment_chance;

	    private int min_turns;

	    private String crit_rate;

	    private int min_hits;

	    private int max_hits;

	    private int max_turns;

	    private String healing;

	    private String drain;

	    private Ailment ailment;

	    public Category getCategory ()
	    {
	        return category;
	    }

	    public void setCategory (Category category)
	    {
	        this.category = category;
	    }

	    public String getStat_chance ()
	    {
	        return stat_chance;
	    }

	    public void setStat_chance (String stat_chance)
	    {
	        this.stat_chance = stat_chance;
	    }

	    public String getFlinch_chance ()
	    {
	        return flinch_chance;
	    }

	    public void setFlinch_chance (String flinch_chance)
	    {
	        this.flinch_chance = flinch_chance;
	    }

	    public String getAilment_chance ()
	    {
	        return ailment_chance;
	    }

	    public void setAilment_chance (String ailment_chance)
	    {
	        this.ailment_chance = ailment_chance;
	    }

	    public int getMin_turns ()
	    {
	        return min_turns;
	    }

	    public void setMin_turns (int min_turns)
	    {
	        this.min_turns = min_turns;
	    }

	    public String getCrit_rate ()
	    {
	        return crit_rate;
	    }

	    public void setCrit_rate (String crit_rate)
	    {
	        this.crit_rate = crit_rate;
	    }

	    public int getMin_hits ()
	    {
	        return min_hits;
	    }

	    public void setMin_hits (int min_hits)
	    {
	        this.min_hits = min_hits;
	    }

	    public int getMax_hits ()
	    {
	        return max_hits;
	    }

	    public void setMax_hits (int max_hits)
	    {
	        this.max_hits = max_hits;
	    }

	    public int getMax_turns ()
	    {
	        return max_turns;
	    }

	    public void setMax_turns (int max_turns)
	    {
	        this.max_turns = max_turns;
	    }

	    public String getHealing ()
	    {
	        return healing;
	    }

	    public void setHealing (String healing)
	    {
	        this.healing = healing;
	    }

	    public String getDrain ()
	    {
	        return drain;
	    }

	    public void setDrain (String drain)
	    {
	        this.drain = drain;
	    }

	    public Ailment getAilment ()
	    {
	        return ailment;
	    }

	    public void setAilment (Ailment ailment)
	    {
	        this.ailment = ailment;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [category = "+category+", stat_chance = "+stat_chance+", flinch_chance = "+flinch_chance+", ailment_chance = "+ailment_chance+", min_turns = "+min_turns+", crit_rate = "+crit_rate+", min_hits = "+min_hits+", max_hits = "+max_hits+", max_turns = "+max_turns+", healing = "+healing+", drain = "+drain+", ailment = "+ailment+"]";
	    }
}
