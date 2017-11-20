package pokemon;

import java.awt.Color;
import java.awt.List;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.function.IntPredicate;

import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.SystemException;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.impl.UserImpl;
import pokeAPI.MoveApi;
import pokeAPI.Moves;
import pokeAPI.PokeApi;

public class BattleModel 
{
	private ArrayList<PokeApi[]> teams = new ArrayList<PokeApi[]>();
	private int[] current_pokemon = new int[2]; 
	private PokeApi[] team1 = new PokeApi[6];
	private PokeApi[] team2 = new PokeApi[6];
	private int turn;
	private boolean battlewon = false;
	private static OkHttpClient client = new OkHttpClient();
	private User trainer1;
	private User trainer2;
	
	public BattleModel(User trainer1, User trainer2)
	{
		this.current_pokemon[0] = 0;
		this.current_pokemon[1] = 0;
		this.trainer1 = trainer1;
		this.trainer2 = trainer2;
		this.teams.add(this.team1);
		this.teams.add(this.team2);
		setupRandomTeam(0);
		System.out.println("finshed team 1 setup");
		setupRandomTeam(1);
		System.out.println("finshed team 2 setup");
		setupIntStats();
		System.out.println("finshed stats setup");
		System.out.println("finshed setup");
		if(!trainer1.hasPrivateChannel())
		{
			trainer1.openPrivateChannel().complete();
		}
		if(!trainer2.hasPrivateChannel())
		{
			trainer2.openPrivateChannel().complete();
		}
		//startBattle();
	}
	private void setupRandomTeam(int team)
	{
		for(int i = 0; i < 6; i++)
		{
			this.teams.get(team)[i] = pickPokemon((int)(Math.random()*720));
			pickMovesTeam1(i, team);
		}

	}
	private void setupIntStats()
	{
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 6; j++)
			{
				this.teams.get(0)[i].setIntStat(j);
				this.teams.get(1)[i].setIntStat(j);
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
	public void pickMovesTeam1(int pokemonidinlist, int team)
	{
		for(int i = 0; i < 4; i++)
		{
			int random = (int)(Math.random()*this.teams.get(team)[pokemonidinlist].getMoves().length);
			String movename = this.teams.get(team)[pokemonidinlist].getMoves()[random].getMove().getName();
			this.teams.get(team)[pokemonidinlist].setNameOfMoves(i, this.teams.get(team)[pokemonidinlist].getMoves()[random].getMove().getName());
			String json = "";
			json = getJson("https://pokeapi.co/api/v2/move/" + movename +"/");
			Gson gson = new Gson();
			MoveApi moveapi = gson.fromJson(json, MoveApi.class);
			this.teams.get(team)[pokemonidinlist].setMoveData(i, moveapi);
			this.teams.get(team)[pokemonidinlist].setNameOfMoves(i, movename);
		}	
	}
	private void startBattle()
	{
		this.current_pokemon[0] = 0;
		this.current_pokemon[1] = 0;
	}
	public void switchPokemon(int switchto, int team)
	{
		if(0 <= switchto && switchto < 6)
			this.current_pokemon[team] = switchto;
	}
	public void useMove()
	{
		//todo
	}
	public boolean checkIfBattleWon()
	{
		return this.battlewon;
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
	public void sendUpdatedBattle()
	{
		((UserImpl)trainer1).getPrivateChannel().sendMessage(toEmbded(0)).queue();
		((UserImpl)trainer1).getPrivateChannel().sendMessage(currentPokemonStringBuilder(0)).queue();
		((UserImpl)trainer2).getPrivateChannel().sendMessage(toEmbded(1)).queue();
		((UserImpl)trainer2).getPrivateChannel().sendMessage(currentPokemonStringBuilder(1)).queue();
	}
	public MessageEmbed toEmbded(int team)
	{
    	EmbedBuilder builder = new EmbedBuilder();
    	builder.setColor(Color.BLUE);
    	for(int i = 0; i < 6; i++)
    	{
    		builder.addField("**" + this.teams.get(team)[i].getName()  + "**", movesStringBuilder(i, team), true);
    	}
		return builder.build();
	}
	public String statsStringBuilder(int teamidnumber, int team)
	{
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < 6; i++)
		{
			builder.append(this.teams.get(team)[teamidnumber].getIntStat(i) + "\n");	
		}
		return builder.toString();
	}
	public String movesStringBuilder(int teamidnumber, int team)
	{
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < 4; i++)
		{
			builder.append(this.teams.get(team)[teamidnumber].getMoveData(i).getName() + "\n");	
		}
		return builder.toString();
	}
	public MessageEmbed currentPokemonStringBuilder(int team)
	{
		EmbedBuilder builder = new EmbedBuilder();
    	builder.setColor(Color.YELLOW);
    	builder.setTitle("Current Pokemon");
    	builder.setThumbnail(this.teams.get(team)[this.current_pokemon[team]].getSprites().getFront_default());
    	builder.appendDescription("**" + teams.get(team)[this.current_pokemon[team]].getName() + "**\n");
    	builder.appendDescription("**Stats:**\n");
    	builder.appendDescription("Health Points : " + teams.get(team)[this.current_pokemon[team]].getIntStat(5) + "\n");
    	builder.appendDescription("Attack            : " + teams.get(team)[this.current_pokemon[team]].getIntStat(4) + "\n");
    	builder.appendDescription("Defense          : " + teams.get(team)[this.current_pokemon[team]].getIntStat(3) + "\n");
    	builder.appendDescription("Special Atk     : " + teams.get(team)[this.current_pokemon[team]].getIntStat(2) + "\n");
    	builder.appendDescription("Special Def     : " + teams.get(team)[this.current_pokemon[team]].getIntStat(1) + "\n");
    	builder.appendDescription("Speed               : " + teams.get(team)[this.current_pokemon[team]].getIntStat(0) + "\n");
    	builder.appendDescription(" \n");
    	builder.appendDescription("**Moves**\n");
    	for (int i = 0; i < 4; i++)
    	{
    		builder.addField(this.teams.get(team)[this.current_pokemon[team]].getNameOfMoves(i), "Power: " + this.teams.get(team)[this.current_pokemon[team]].getMoveData(i).getPower() + "\n" + "Accruacy: " + this.teams.get(team)[this.current_pokemon[team]].getMoveData(i).getAccuracy() + "\n" , true);
    	}
    	return builder.build();
	}
	public MessageEmbed publicBattleView()
	{
		EmbedBuilder builder = new EmbedBuilder();
    	builder.setColor(Color.magenta);
    	builder.setTitle(this.trainer1 + " VS " + this.trainer2);
    	//builder.setImage(teams.get(0)[this.currentpokemont1].getSprites().getFront_default());
    	builder.setDescription(teams.get(0)[this.current_pokemon[0]].getSprites().getFront_default() + teams.get(1)[this.current_pokemon[1]].getSprites().getFront_default());
    	return builder.build();
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
