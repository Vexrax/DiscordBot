package pokemon;

public class BattleModel 
{
	private Pokemon[] team1;
	private Pokemon[] team2;
	private String trainer1;
	private String trainer2;
	private int currentpokemont1;
	private int currentpokemont2;
	private int turn;
	
	BattleModel(String trainer1, String trainer2)
	{
		this.trainer1 = trainer1;
		this.trainer2 = trainer2;
		this.team1 = setupRandomTeam();
		this.team2 = setupRandomTeam();
		startBattle();
	}
	private Pokemon[] setupRandomTeam()
	{
		Pokemon[] team =  new Pokemon[6];
		for(int i = 0; i < 6; i++)
			team[i] = pickPokemon((int)(Math.random()*720));
		return team;
	}
	private Pokemon pickPokemon(int pokemonID)
	{
		//fetch pokemon data based on id and return a pokemon based on that data
		//this will also need to randomize moves based on what moves it can learn.
		//might need random gender here too.
		return new Pokemon(); //USING THIS TEMP UNTIL I HOOK TO DB
	}
	private void startBattle()
	{
		this.currentpokemont1 = 0;
		this.currentpokemont2 = 0;
	}
	public void switchPokemon()
	{
		//todo
	}
	public void useMove()
	{
		//todo
	}
	public boolean checkIfBattleWon()
	{
		return true;
		//todo
	}
	private int damageCalculation(Move move, Pokemon pokemon)
	{
		return 0;
		//the algorithm that calculates the damage done to a pokemon after a move. 
	}
	private void StatusEffectActivates(Pokemon pokemon)
	{
		//todo
	}
	public String toString()
	{
		//String repr of the pokemon battle.
		return "todo";
	}
	
}
