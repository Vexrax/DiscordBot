package Commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class QuoteCommand implements Command
{
	private final String HELP = "Usage: = ~//quote";
	public boolean called(String[] args, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		return true;
	}

	public void action(String[] args, MessageReceivedEvent e) {
        MessageChannel objChannel = e.getChannel();

		String path = "quoteFile.txt";
    	try
    	{
    		BufferedReader textreader = new BufferedReader(new FileReader(path));
    		int numberoflines = 0;
    		while(textreader.readLine() != null)
    		{
    			numberoflines ++;
    		}
    		textreader.close();
    		String[] quotearray = new String[numberoflines]; 
    		BufferedReader textreaderforquotes = new BufferedReader(new FileReader(path));
    		for(int i = 0; i < numberoflines; i++)
    		{
    			quotearray[i] = textreaderforquotes.readLine();
    		}
    		objChannel.sendMessage(quotearray[(int)(Math.random()*numberoflines)]).queue();
    		textreaderforquotes.close();
    		
    	}  	
    	catch(IOException exception) 
    	{
    		objChannel.sendMessage("Somethng went wrong, missing quotefile string, check App.java").queue();
    	} 			
	}

	public String help() {
		// TODO Auto-generated method stub
		return HELP;
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
