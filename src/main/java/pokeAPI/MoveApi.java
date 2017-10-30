package pokeAPI;

public class MoveApi 
{
	 private Flavor_text_entries[] flavor_text_entries;

	    private Effect_entries[] effect_entries;

	    private Damage_class damage_class;

	    private Type type;

	    private Meta meta;

	    private Machines[] machines;

	    private String id;

	    private double effect_chance;

	    //private String[] stat_changes;

	    private Names[] names;

	    private String priority;

	    private String name;

	    private Target target;

	    private String power;

	    private String pp;

	    private String accuracy;

	    private Generation generation;
	    
	    private Ailment ailment;

	    public Flavor_text_entries[] getFlavor_text_entries ()
	    {
	        return flavor_text_entries;
	    }

	    public void setFlavor_text_entries (Flavor_text_entries[] flavor_text_entries)
	    {
	        this.flavor_text_entries = flavor_text_entries;
	    }

	    public Effect_entries[] getEffect_entries ()
	    {
	        return effect_entries;
	    }

	    public void setEffect_entries (Effect_entries[] effect_entries)
	    {
	        this.effect_entries = effect_entries;
	    }

	    public Damage_class getDamage_class ()
	    {
	        return damage_class;
	    }

	    public void setDamage_class (Damage_class damage_class)
	    {
	        this.damage_class = damage_class;
	    }


	    public Type getType ()
	    {
	        return type;
	    }

	    public void setType (Type type)
	    {
	        this.type = type;
	    }

	    public Meta getMeta ()
	    {
	        return meta;
	    }

	    public void setMeta (Meta meta)
	    {
	        this.meta = meta;
	    }

	    public Machines[] getMachines ()
	    {
	        return machines;
	    }

	    public void setMachines (Machines[] machines)
	    {
	        this.machines = machines;
	    }


	    public String getId ()
	    {
	        return id;
	    }

	    public void setId (String id)
	    {
	        this.id = id;
	    }

	    public double getEffect_chance ()
	    {
	        return effect_chance;
	    }

	    public void setEffect_chance (double effect_chance)
	    {
	        this.effect_chance = effect_chance;
	    }

	    /*public String[] getStat_changes ()
	    {
	        return stat_changes;
	    }

	    public void setStat_changes (String[] stat_changes)
	    {
	        this.stat_changes = stat_changes;
	    }*/

	    public Names[] getNames ()
	    {
	        return names;
	    }

	    public void setNames (Names[] names)
	    {
	        this.names = names;
	    }

	    public String getPriority ()
	    {
	        return priority;
	    }

	    public void setPriority (String priority)
	    {
	        this.priority = priority;
	    }

	    public String getName ()
	    {
	        return name;
	    }

	    public void setName (String name)
	    {
	        this.name = name;
	    }

	    public Target getTarget ()
	    {
	        return target;
	    }

	    public void setTarget (Target target)
	    {
	        this.target = target;
	    }

	    public String getPower ()
	    {
	        return power;
	    }

	    public void setPower (String power)
	    {
	        this.power = power;
	    }


	    public String getPp ()
	    {
	        return pp;
	    }

	    public void setPp (String pp)
	    {
	        this.pp = pp;
	    }

	    public String getAccuracy ()
	    {
	        return accuracy;
	    }

	    public void setAccuracy (String accuracy)
	    {
	        this.accuracy = accuracy;
	    }

	    public Generation getGeneration ()
	    {
	        return generation;
	    }

	    public void setGeneration (Generation generation)
	    {
	        this.generation = generation;
	    }


	    @Override
	    public String toString()
	    {
	    	return "";
	    }

		public Ailment getAilment() {
			return ailment;
		}

		public void setAilment(Ailment ailment) {
			this.ailment = ailment;
		}
}
