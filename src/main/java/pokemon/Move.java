package pokemon;

public class Move 
{
	private String name;
	private int power;
	private String type;
	private int PP;
	private int effect;
	private double accuracy;
	
	Move(String name, int power, String type, int PP, int effect, double accuraccy)
	{
		this.name = name;
		this.power = power;
		this.type = type;
		this.PP = PP;
		this.effect = effect;
		this.accuracy = accuraccy;
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

