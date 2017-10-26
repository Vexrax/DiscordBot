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
	private Pokemon[] team1;
	private Pokemon[] team2;
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
		//this.team1 = setupRandomTeam();
		//this.team2 = setupRandomTeam();
		//startBattle();
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
						   generateMoves(pokeApi),
						   new Status("none", -1),
						   checkAbility(),
						   Integer.parseInt(pokeApi.getWeight()));
	}
	private int checkAbility()
	{
		//todo
		//pick a ability from the ability set;
		return 0;
	}
	@SuppressWarnings("unused")
	private Move[] generateMoves(PokeApi pokeApi)
	{
		Move[] moves = new Move[4];
		Moves[] listofmoves = pokeApi.getMoves();
		for(int i = 0; i < 4; i++)
		{
			int chosenmove = (int)(Math.random()*listofmoves.length);
			String move =  listofmoves[chosenmove].getMove().getName();
			String json = "";
			try 
			{
				json = getJson("https://pokeapi.co/api/v2/move/" + move +"/");
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			Gson gson = new Gson();
			MoveApi moveApi = gson.fromJson(json, MoveApi.class);
			Move current_move = new Move(move, 
										 Integer.parseInt(moveApi.getPower()),
									     moveApi.getType().toString(),
										 Integer.parseInt(moveApi.getPp()),
										 0,  //needs to impliment effectlist
										 Integer.parseInt(moveApi.getAccuracy()),
										 moveApi.getDamage_class().getName());
			moves[i] = current_move;
		}
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
		return "|" +this.trainer1 +"|" + "                                        " + "|" +this.trainer2 +"|\n"
			+  "|CURRENT POKEMON|                                        |CURRENT POKEMON|\n"
			+  "{MOVE 1      1/1}                                        {MOVE 1      1/1}\n"
			+  "{MOVE 2      1/1}                                        {MOVE 2      1/1}\n"
			+  "{MOVE 3      1/1}                                        {MOVE 3      1/1}\n"
			+  "{MOVE 4      1/1}                                        {MOVE 4      0/1}\n"
			+  "|   POKEMON 2   |                                        |   POKEMON 2   |\n"
			+  "|   POKEMON 3   |                                        |   POKEMON 3   |\n"
			+  "|   POKEMON 4   |                                        |   POKEMON 4   |\n"
			+  "|   POKEMON 5   |                                        |   POKEMON 5   |\n"
			+  "|   POKEMON 6   |                                        |   POKEMON 6   |\n";
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
