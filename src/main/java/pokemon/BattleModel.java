package pokemon;

import java.io.IOException;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import pokeAPI.MoveApi;
import pokeAPI.Moves;
import pokeAPI.PokeApi;

public class BattleModel 
{
	private PokeApi[] team1 = new PokeApi[6];
	private PokeApi[] team2 = new PokeApi[6];
	private String trainer1;
	private String trainer2;
	private int currentpokemont1;
	private int currentpokemont2;
	private int turn;
	private static OkHttpClient client = new OkHttpClient();
	
	public BattleModel(String trainer1, String trainer2)
	{
		this.trainer1 = trainer1;
		this.trainer2 = trainer2;
		setupRandomTeam();
		//this.team2 = setupRandomTeam();
		//startBattle();
	}
	private void setupRandomTeam()
	{
		for(int i = 0; i < 6; i++)
		{
			this.team1[i] = pickPokemon((int)(Math.random()*720));
		}
	}
	private void setupRandomTeam2()
	{
		for(int i = 0; i < 6; i++)
		{
			this.team2[i] = pickPokemon((int)(Math.random()*720));
		}
	}
	private PokeApi pickPokemon(int pokemonID) 
	{
		String json = "";
		json = getJson("https://pokeapi.co/api/v2/pokemon/" + pokemonID +"/");
		Gson gson = new Gson();
		PokeApi PokeApi = gson.fromJson(json, PokeApi.class);
		return PokeApi;
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
	private int damageCalculation(MoveApi move, PokeApi pokemon)
	{
		return 0;
		//the algorithm that calculates the damage done to a pokemon after a move. 
	}
	private void StatusEffectActivates(PokeApi pokemon)
	{
		//todo
	}
	public String toString()
	{
		//String repr of the pokemon battle.
		return this.team1[0].getName() + this.team1[1].getMoves();
	}
	public static String getJson(String url) 
	{
		 try 
		 {
			Request request = new Request.Builder()
			      .url(url)
			      .build();

			  Response response = client.newCall(request).execute();
			  return response.body().string();
		 }
		 catch(IOException e)
		 {
			 e.printStackTrace();
			 return "error";
		 }
	}
	
}
