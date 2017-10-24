package pokemon;

public class BattleModel 
{
	private Pokemon[] team1;
	private Pokemon[] team2;
	private String trainer1;
	private String trainer2;
	
	BattleModel(String trainer1, String trainer2)
	{
		this.trainer1 = trainer1;
		this.trainer2 = trainer2;
		this.team1 = setupRandomTeam();
		this.team2 = setupRandomTeam();
	}
	private Pokemon[] setupRandomTeam()
	{
		Pokemon[] team =  new Pokemon[6];
		for(int i = 0; i < 6; i++)
			team[i] = pickPokemon((int)(Math.random()*700));
		return team;
	}
	private Pokemon pickPokemon(int pokemonID)
	{
		//fetch pokemon data based on id and return a pokemon based on that data
		return new Pokemon(); //USING THIS TEMP UNTIL I HOOK TO DB
	}
}
