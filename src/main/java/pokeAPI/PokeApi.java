package pokeAPI;

public class PokeApi
{
	
	    private String location_area_encounters;

	    private String weight;

	    private Species species;

	    private Stats[] stats;

	    private Moves[] moves;

	    private String is_default;

	    private String[] held_items;

	    private String id;

	    private String base_experience;

	    private String height;

	    private String order;

	    private Abilities[] abilities;

	    private Game_indices[] game_indices;

	    private String name;

	    private Sprites sprites;

	    private Types[] types;

	    private Forms[] forms;

	    public String getLocation_area_encounters ()
	    {
	        return location_area_encounters;
	    }

	    public void setLocation_area_encounters (String location_area_encounters)
	    {
	        this.location_area_encounters = location_area_encounters;
	    }

	    public String getWeight ()
	    {
	        return weight;
	    }

	    public void setWeight (String weight)
	    {
	        this.weight = weight;
	    }

	    public Species getSpecies ()
	    {
	        return species;
	    }

	    public void setSpecies (Species species)
	    {
	        this.species = species;
	    }

	    public Stats[] getStats ()
	    {
	        return stats;
	    }

	    public void setStats (Stats[] stats)
	    {
	        this.stats = stats;
	    }

	    public Moves[] getMoves ()
	    {
	        return moves;
	    }

	    public void setMoves (Moves[] moves)
	    {
	        this.moves = moves;
	    }

	    public String getIs_default ()
	    {
	        return is_default;
	    }

	    public void setIs_default (String is_default)
	    {
	        this.is_default = is_default;
	    }

	    public String[] getHeld_items ()
	    {
	        return held_items;
	    }

	    public void setHeld_items (String[] held_items)
	    {
	        this.held_items = held_items;
	    }

	    public String getId ()
	    {
	        return id;
	    }

	    public void setId (String id)
	    {
	        this.id = id;
	    }

	    public String getBase_experience ()
	    {
	        return base_experience;
	    }

	    public void setBase_experience (String base_experience)
	    {
	        this.base_experience = base_experience;
	    }

	    public String getHeight ()
	    {
	        return height;
	    }

	    public void setHeight (String height)
	    {
	        this.height = height;
	    }

	    public String getOrder ()
	    {
	        return order;
	    }

	    public void setOrder (String order)
	    {
	        this.order = order;
	    }

	    public Abilities[] getAbilities ()
	    {
	        return abilities;
	    }

	    public void setAbilities (Abilities[] abilities)
	    {
	        this.abilities = abilities;
	    }

	    public Game_indices[] getGame_indices ()
	    {
	        return game_indices;
	    }

	    public void setGame_indices (Game_indices[] game_indices)
	    {
	        this.game_indices = game_indices;
	    }

	    public String getName ()
	    {
	        return name;
	    }

	    public void setName (String name)
	    {
	        this.name = name;
	    }

	    public Sprites getSprites ()
	    {
	        return sprites;
	    }

	    public void setSprites (Sprites sprites)
	    {
	        this.sprites = sprites;
	    }

	    public Types[] getTypes ()
	    {
	        return types;
	    }

	    public void setTypes (Types[] types)
	    {
	        this.types = types;
	    }

	    public Forms[] getForms ()
	    {
	        return forms;
	    }

	    public void setForms (Forms[] forms)
	    {
	        this.forms = forms;
	    }
	    public String[] getTypesAsString()
	    {
	    	String[] returnval = new String[2];
	    	returnval[0] = this.types[0].toString();
	    	returnval[1] = this.types[1].toString();
	    	return returnval;
	    }
	    @Override
	    public String toString()
	    {
	        return "ClassPojo [location_area_encounters = "+location_area_encounters+", weight = "+weight+", species = "+species+", stats = "+stats+", moves = "+moves+", is_default = "+is_default+", held_items = "+held_items+", id = "+id+", base_experience = "+base_experience+", height = "+height+", order = "+order+", abilities = "+abilities+", game_indices = "+game_indices+", name = "+name+", sprites = "+sprites+", types = "+types+", forms = "+forms+"]";
	    }
}
