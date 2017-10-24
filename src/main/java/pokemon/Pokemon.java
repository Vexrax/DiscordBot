package pokemon;

public class Pokemon 
{
	private String name;
	private String[] type;
	private int health_points;
	private int attack;
	private int defense;
	private int sp_attack;
	private int sp_defense;
	private int speed;
	private Move[] moves;
	private Status status;

	public Pokemon(String name, String[] type, int health, int attack, int defense, int sp_attack, int sp_defense, int speed, Move[] moves, Status status)
	{
		this.setName(name);
		this.type = type;
		this.setHealth_points(health);
		this.setAttack(attack);
		this.setDefense(defense);
		this.setSp_attack(sp_attack);
		this.setSp_defense(sp_defense);
		this.setSpeed(speed);
		this.moves = moves;
		this.setStatus(status);			
	}
	public Pokemon() //ONLY FOR USE IN TESTING DELETE AFTER COMPLETE
	{
		this.setName("me");
		this.type[0] = "dragon";
		this.type[1] = "dark";
		this.setHealth_points(1000);
		this.setAttack(300);
		this.setDefense(300);
		this.setSp_attack(300);
		this.setSp_defense(300);
		this.setSpeed(300);
		this.moves[0] = new Move( "some attack",100, "dragon" , 10, 1);
		this.moves[1] = new Move( "some attack",100, "dragon" , 10, 1);
		this.moves[2] = new Move( "some attack",100, "dragon" , 10, 1);
		this.moves[3] = new Move( "some attack",100, "dragon" , 10, 1);
		this.setStatus(new Status("none"));	
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	public int getHealth_points() 
	{
		return health_points;
	}

	public void setHealth_points(int health_points) 
	{
		this.health_points += health_points;
	}

	public int getAttack() 
	{
		return attack;
	}

	public void setAttack(int attack) 
	{
		this.attack += attack;
	}

	public int getDefense() 
	{
		return defense;
	}

	public void setDefense(int defense) 
	{
		this.defense += defense;
	}

	public int getSp_attack() 
	{
		return sp_attack;
	}

	public void setSp_attack(int sp_attack) 
	{
		this.sp_attack += sp_attack;
	}

	public int getSp_defense() 
	{
		return sp_defense;
	}

	public void setSp_defense(int sp_defense) 
	{
		this.sp_defense += sp_defense;
	}

	public int getSpeed() 
	{
		return speed;
	}

	public void setSpeed(int speed) 
	{
		this.speed += speed;
	}

	public Move[] getMoves() {
		return moves;
	}

	public void setMoves(Move[] moves, Move old_move) 
	{
		//todo
		//replace old move with new move
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void onSwitchOut()
	{
		//fetch the default stats for the pokemon.
	}
	private void resetStats()
	{
		//For use on switchout
		//resets to default stats.
	}



}
