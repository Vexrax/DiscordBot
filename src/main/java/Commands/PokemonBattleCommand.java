package Commands;

import org.slf4j.Marker;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import pokemon.BattleModel;

public class PokemonBattleCommand implements Command
{
	BattleModel battle;
	public boolean called(String[] args, MessageReceivedEvent e) 
	{
		System.out.println(args[0]);
		if(args[0].equals("newbattle")) //make sure to check that theres a user in hte mentions
		{
			return true;
		}
		else if(args[0].equals("switch"))
		{
			return true;
		}
		else if(args[0].equals("move")) //check if mvoe number is valid (0-4)
		{
			return true;
		}
		//Add more if statements for battle commands
		return false;
	}

	public void action(String[] args, MessageReceivedEvent e) 
	{
        Message objMsg = e.getMessage();
        MessageChannel objChannel = e.getChannel();
        User objUser = e.getAuthor();   
        TextChannel objTextChannel = e.getTextChannel();
    	if(args[0].equals("newbattle"))
    	{
    		NewBattle(e);
    	}
    	else if (args[0].equals("switch"))
    	{
    		System.out.println(args[1]);
    		Switch(args[1], e);
    	}
    	else if(args[0].equals("move"))
    	{
    		useMove(Integer.parseInt(args[1]), e);
    	}
    	battle.sendUpdatedBattle();		
	}

	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

	public void executed(boolean success, MessageReceivedEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void NewBattle(MessageReceivedEvent e)
	{
        TextChannel objTextChannel = e.getTextChannel();
        Message objMsg = e.getMessage(); //use later to set up trainer 2
        User objUser = e.getAuthor();  
        objTextChannel.sendMessage("Setting up a new battle").queue();
		battle = new BattleModel(objUser, e.getMessage().getMentionedUsers().get(0), objTextChannel);
	}
	public void Switch(String switchnumber, MessageReceivedEvent e)
	{
        TextChannel objTextChannel = e.getTextChannel();
		try
		{
			battle.switchPokemon(Integer.parseInt(switchnumber), e.getAuthor()); //using 0 for now CHAGE TO PROPPER LOGIC LATER
		}
		catch(Exception exception)
		{
			objTextChannel.sendMessage("please select a number from 0-5").queue();
		}
	}
	public void useMove(int movenumber,MessageReceivedEvent e)
	{
		try 
		{
			battle.useMove(movenumber, e.getAuthor());
		}
		catch(Exception exception)
		{
			System.out.println("exception at line 93 pokemonbattlecommand");
		}
	}
}
