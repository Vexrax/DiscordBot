package pokemon;

public class Move 
{
	private int power;
	private String type;
	private int PP;
	private int effect;
	
	Move(int power, String type, int PP, int effect)
	{
		this.power = power;
		this.type = type;
		this.PP = PP;
		this.effect = effect;
	}
}
