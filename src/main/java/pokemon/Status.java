package pokemon;

public class Status 
{
	private String status_name;
	private int duration;
	
	Status(String status_name)
	{
		this.status_name = status_name;
		this.duration = -1;
	}
	Status(String status_name, int duration)
	{
		this.status_name = status_name;
		this.duration = duration;
	}
	public String getStatus()
	{
		return this.status_name;
	}
	public int getDuration()
	{
		return this.duration;
	}
	public void durationTickDown()
	{
		if(this.duration != -1)
			this.duration -= 1;
	}
}
