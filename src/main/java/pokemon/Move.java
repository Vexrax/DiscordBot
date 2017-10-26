package pokemon;

public class Move 
{
	private String name;
	private int power;
	private String type;
	private int PP;
	private int effect;
	private int accuracy;
	private int specialorphysical;
	
	Move(String name, int power, String type, int PP, int effect, int accuraccy, String specialorphysical)
	{
		this.name = name;
		this.power = power;
		this.type = type;
		this.PP = PP;
		this.effect = effect;
		this.accuracy = accuraccy;
		if(specialorphysical.equals("special"))
			this.specialorphysical = 1;
		else
		{	
			this.specialorphysical = 0;
		}
		
	}
	
	public int getPP()
	{
		return this.PP;
	}
	public String getType()
	{
		return this.type;
	}
	public int getPower()
	{
		return this.power;
	}
	public void useMove()
	{
		this.PP -= 1;
	}
}

