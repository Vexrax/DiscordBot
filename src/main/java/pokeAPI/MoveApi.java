package pokeAPI;

public class MoveApi 
{
	 private Flavor_text_entries[] flavor_text_entries;

	    private Effect_entries[] effect_entries;

	    private String[] effect_changes;

	    private Damage_class damage_class;

	    private Contest_type contest_type;

	    private Type type;

	    private Meta meta;

	    private Machines[] machines;

	    private String[] past_values;

	    private String id;

	    private double effect_chance;

	    private String[] stat_changes;

	    private Names[] names;

	    private String priority;

	    private String name;

	    private Target target;

	    private String power;

	    private Contest_effect contest_effect;

	    private Super_contest_effect super_contest_effect;

	    private String pp;

	    private String accuracy;

	    private Generation generation;

	    private Contest_combos contest_combos;

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

	    public String[] getEffect_changes ()
	    {
	        return effect_changes;
	    }

	    public void setEffect_changes (String[] effect_changes)
	    {
	        this.effect_changes = effect_changes;
	    }

	    public Damage_class getDamage_class ()
	    {
	        return damage_class;
	    }

	    public void setDamage_class (Damage_class damage_class)
	    {
	        this.damage_class = damage_class;
	    }

	    public Contest_type getContest_type ()
	    {
	        return contest_type;
	    }

	    public void setContest_type (Contest_type contest_type)
	    {
	        this.contest_type = contest_type;
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

	    public String[] getPast_values ()
	    {
	        return past_values;
	    }

	    public void setPast_values (String[] past_values)
	    {
	        this.past_values = past_values;
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

	    public String[] getStat_changes ()
	    {
	        return stat_changes;
	    }

	    public void setStat_changes (String[] stat_changes)
	    {
	        this.stat_changes = stat_changes;
	    }

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

	    public Contest_effect getContest_effect ()
	    {
	        return contest_effect;
	    }

	    public void setContest_effect (Contest_effect contest_effect)
	    {
	        this.contest_effect = contest_effect;
	    }

	    public Super_contest_effect getSuper_contest_effect ()
	    {
	        return super_contest_effect;
	    }

	    public void setSuper_contest_effect (Super_contest_effect super_contest_effect)
	    {
	        this.super_contest_effect = super_contest_effect;
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

	    public Contest_combos getContest_combos ()
	    {
	        return contest_combos;
	    }

	    public void setContest_combos (Contest_combos contest_combos)
	    {
	        this.contest_combos = contest_combos;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [flavor_text_entries = "+flavor_text_entries+", effect_entries = "+effect_entries+", effect_changes = "+effect_changes+", damage_class = "+damage_class+", contest_type = "+contest_type+", type = "+type+", meta = "+meta+", machines = "+machines+", past_values = "+past_values+", id = "+id+", effect_chance = "+effect_chance+", stat_changes = "+stat_changes+", names = "+names+", priority = "+priority+", name = "+name+", target = "+target+", power = "+power+", contest_effect = "+contest_effect+", super_contest_effect = "+super_contest_effect+", pp = "+pp+", accuracy = "+accuracy+", generation = "+generation+", contest_combos = "+contest_combos+"]";
	    }
}
