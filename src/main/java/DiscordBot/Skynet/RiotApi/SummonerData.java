package DiscordBot.Skynet.RiotApi;

public class SummonerData 
{
    private String id;

    private String accountId;

    private String name;

    private String profileIconId;

    private String revisionDate;

    private String summonerLevel;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getAccountId ()
    {
        return accountId;
    }

    public void setAccountId (String accountId)
    {
        this.accountId = accountId;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getProfileIconId ()
    {
        return profileIconId;
    }

    public void setProfileIconId (String profileIconId)
    {
        this.profileIconId = profileIconId;
    }

    public String getRevisionDate ()
    {
        return revisionDate;
    }

    public void setRevisionDate (String revisionDate)
    {
        this.revisionDate = revisionDate;
    }

    public String getSummonerLevel ()
    {
        return summonerLevel;
    }

    public void setSummonerLevel (String summonerLevel)
    {
        this.summonerLevel = summonerLevel;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", accountId = "+accountId+", name = "+name+", profileIconId = "+profileIconId+", revisionDate = "+revisionDate+", summonerLevel = "+summonerLevel+"]";
    }
}
