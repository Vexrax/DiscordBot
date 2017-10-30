package pokemon;

import java.awt.Color;
import java.io.IOException;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
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
		setupIntStats();
		//this.team2 = setupRandomTeam();
		//startBattle();
	}
	private void setupRandomTeam()
	{
		for(int i = 0; i < 6; i++)
		{
			this.team1[i] = pickPokemon((int)(Math.random()*720));
			pickMovesTeam1(i);
		}
		for(int i = 0; i < 6; i++)
		{
			this.team2[i] = pickPokemon((int)(Math.random()*720));
			pickMovesTeam2(i);

		}
	}
	private void setupIntStats()
	{
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 6; j++)
			{
				this.team1[i].setIntStat(j);
				this.team2[i].setIntStat(j);
			}
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
	public void pickMovesTeam1(int pokemonidinlist)
	{
		for(int i = 0; i < 4; i++)
		{
			int random = (int)(Math.random()*this.team1[pokemonidinlist].getMoves().length);
			String movename = this.team1[pokemonidinlist].getMoves()[random].getMove().getName();
			this.team1[pokemonidinlist].setNameOfMoves(i, this.team1[pokemonidinlist].getMoves()[random].getMove().getName());
			String json = "";
			json = getJson("https://pokeapi.co/api/v2/move/" + movename +"/");
			Gson gson = new Gson();
			MoveApi moveapi = gson.fromJson(json, MoveApi.class);
			this.team1[pokemonidinlist].setMoveData(i, moveapi);
			this.team1[pokemonidinlist].setNameOfMoves(i, movename);
		}	
	}
	public void pickMovesTeam2(int pokemonidinlist)
	{
		for(int i = 0; i < 4; i++)
		{
			int random = (int)(Math.random()*this.team1[pokemonidinlist].getMoves().length);
			String movename = this.team1[pokemonidinlist].getMoves()[random].getMove().getName();
			this.team1[pokemonidinlist].setNameOfMoves(i, this.team1[pokemonidinlist].getMoves()[random].getMove().getName());
			String json = "";
			json = getJson("https://pokeapi.co/api/v2/move/" + movename +"/");
			Gson gson = new Gson();
			MoveApi moveapi = gson.fromJson(json, MoveApi.class);
			this.team1[pokemonidinlist].setMoveData(i, moveapi);
			this.team1[pokemonidinlist].setNameOfMoves(i, movename);
		}
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
		return "ima do this later";
	}
	public MessageEmbed toEmbded()
	{
    	EmbedBuilder builder = new EmbedBuilder();
    	builder.setColor(Color.BLUE);
    	for(int i = 0; i < 6; i++)
    	{
    		builder.addField("**" + this.team1[i].getName()  + "**", movesStringBuilder(i), true);
    	}
		return builder.build();
	}
	public String statsStringBuilder(int teamidnumber)
	{
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < 6; i++)
		{
			builder.append(this.team1[teamidnumber].getIntStat(i) + "\n");	
		}
		return builder.toString();
	}
	public String movesStringBuilder(int teamidnumber)
	{
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < 4; i++)
		{
			builder.append(this.team1[teamidnumber].getMoveData(i).getName() + "\n");	
		}
		return builder.toString();
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
