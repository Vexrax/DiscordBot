package pokemon;

import java.io.IOException;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import pokeAPI.PokeApi;

public class BattleModel 
{
	private Pokemon[] team1;
	private Pokemon[] team2;
	private String trainer1;
	private String trainer2;
	private int currentpokemont1;
	private int currentpokemont2;
	private int turn;
	private static OkHttpClient client = new OkHttpClient();
	
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
	private Pokemon pickPokemon(int pokemonID) //change to nonstatic
	{
		String json = "";
		try 
		{
			json = getJson("https://pokeapi.co/api/v2/pokemon/" + pokemonID +"/");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Gson gson = new Gson();
		PokeApi pokeApi = gson.fromJson(json, PokeApi.class);
		return new Pokemon(pokeApi.getName(), 
						   pokeApi.getTypesAsString(), 
						   (int)(Math.random()*2) , 
						   Integer.parseInt(pokeApi.getStats()[5].toString()),
						   Integer.parseInt(pokeApi.getStats()[4].toString()), 
						   Integer.parseInt(pokeApi.getStats()[3].toString()),
						   Integer.parseInt(pokeApi.getStats()[2].toString()),
						   Integer.parseInt(pokeApi.getStats()[1].toString()),
						   Integer.parseInt(pokeApi.getStats()[0].toString()),
						   1.00,
						   generateMoves(),
						   new Status("none", -1),
						   checkAbility(),
						   Integer.parseInt(pokeApi.getWeight()));
	}
	private int checkAbility()
	{
		//todo
		return 0;
	}
	private Move[] generateMoves()
	{
		Move[] moves = new Move[4];
		return moves;
		//todo
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
	public static String getJson(String url) throws IOException 
	{
		  Request request = new Request.Builder()
			      .url(url)
			      .build();

			  Response response = client.newCall(request).execute();
			  return response.body().string();
	}
	
}
