package Commands;


import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import pokemon.BattleModel;

import java.util.ArrayList;

public class PokemonBattleCommand implements Command
{
	private ArrayList<String> valid_commands;
	BattleModel battle;
	PokemonBattleCommand()
	{
		valid_commands = new ArrayList<String>();
		valid_commands.add("newbattle");
		valid_commands.add("switch");
		valid_commands.add("move");
	}
	public boolean called(String[] args, MessageReceivedEvent e) 
	{
		if(args.length == 0)
		{
			return false;
		}
		System.out.println(args[0]);
		return valid_commands.contains(args[0]);
	}

	public void action(String[] args, MessageReceivedEvent e) 
	{
        
    	if(args[0].equals("newbattle"))
    	{
    		NewBattle(e);
    	}
    	else if (args[0].equals("switch") || (args[0].equals("move")))
    	{
    		System.out.println(args[1]);
    		battle.selectAction(args[0], Integer.parseInt(args[1]), e.getAuthor());
    	}
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

}
