package pokeAPI;

public class Names 
{
    private String name;

    private Language language;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Language getLanguage ()
    {
        return language;
    }

    public void setLanguage (Language language)
    {
        this.language = language;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", language = "+language+"]";
    }
}
