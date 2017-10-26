package pokeAPI;

public class Flavor_text_entries
{
	private Version_group version_group;

    private Language language;

    private String flavor_text;

    public Version_group getVersion_group ()
    {
        return version_group;
    }

    public void setVersion_group (Version_group version_group)
    {
        this.version_group = version_group;
    }

    public Language getLanguage ()
    {
        return language;
    }

    public void setLanguage (Language language)
    {
        this.language = language;
    }

    public String getFlavor_text ()
    {
        return flavor_text;
    }

    public void setFlavor_text (String flavor_text)
    {
        this.flavor_text = flavor_text;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [version_group = "+version_group+", language = "+language+", flavor_text = "+flavor_text+"]";
    }
}
