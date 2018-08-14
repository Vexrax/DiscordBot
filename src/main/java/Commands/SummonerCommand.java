package Commands;

import java.awt.Color;
import java.io.IOException;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import RiotApi.RankData;
import RiotApi.SummonerData;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SummonerCommand implements Command
{
	String API_KEY;
	private static OkHttpClient client = new OkHttpClient();

	public SummonerCommand(String apiKey)
	{
		this.API_KEY = apiKey;
	}

	public boolean called(String[] args, MessageReceivedEvent e) {
		if(args[0].length() > 0)
			return true;
		return false;
	}

	public void action(String[] args, MessageReceivedEvent e) 
	{
		try 
		{
			String json = "";
			EmbedBuilder builder = new EmbedBuilder();

			json = getJson("https://na1.api.riotgames.com/lol/summoner/v3/summoners/by-name/" + args[0] + "?api_key=" + API_KEY);
			Gson gson = new Gson();
			SummonerData SummonerData = gson.fromJson(json, SummonerData.class);
			
			json = getJson("https://na1.api.riotgames.com/lol/league/v3/positions/by-summoner/" + SummonerData.getId() + "?api_key=" + API_KEY);
			System.out.println(json);
			Gson gsonRanks = new Gson();
			RankData[] rankData = gsonRanks.fromJson(json, RankData[].class);
			
			builder.setTitle("Summoner: " + args[0]);
			builder.setThumbnail("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/" + SummonerData.getProfileIconId() + ".png");
			builder.setColor(Color.CYAN);
			builder.appendDescription("Name: " + SummonerData.getName() + "\n");
			builder.appendDescription("Level: " + SummonerData.getSummonerLevel() + "\n");
			for(int i = 0; i < rankData.length; i++)
			{
				builder.appendDescription(rankData[i].getQueueType() + ": **" +rankData[i].getTier() + " " + rankData[i].getRank() + " **\n");
				builder.appendDescription("W: " + rankData[i].getWins() + " L: " + rankData[i].getLosses() + "\n");
			}
			e.getMessage().getTextChannel().sendMessage(builder.build()).queue();
			System.out.println(SummonerData.getId());
		}
		catch(Exception exception)
		{
			System.out.println(exception.toString());
			e.getMessage().getTextChannel().sendMessage("Summoner could not be found.").queue();
		}
	}

	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	public void executed(boolean success, MessageReceivedEvent e) {
		// TODO Auto-generated method stub
		
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
