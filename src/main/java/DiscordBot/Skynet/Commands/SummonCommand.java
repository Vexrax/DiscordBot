package DiscordBot.Skynet.Commands;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SummonCommand implements Command
{
	
	
	public boolean called(String[] args, MessageReceivedEvent e) 
	{
		return true;
	}

	public void action(String[] args, MessageReceivedEvent e)
	{
		Guild objGuild = e.getGuild();
		User objUser = e.getAuthor();
		TextChannel objTextChannel = e.getTextChannel();
		
		if(objGuild == null)
			return;
		if(objGuild.getAudioManager().isConnected())
		{
			objGuild.getAudioManager().closeAudioConnection();
		}
        if(!objGuild.getAudioManager().isConnected() && !objGuild.getAudioManager().isAttemptingToConnect())
		{
			VoiceChannel voicechannel = objGuild.getMember(objUser).getVoiceState().getChannel();
			if(voicechannel == null)
			{
					objTextChannel.sendMessage("You must join a channel first").queue();
			}
			objGuild.getAudioManager().openAudioConnection(voicechannel);
			objTextChannel.sendMessage("I AM HERE").queue();
		}
        
	}

	public String help() {
		return "Summon a bot to the channel";
	}

	public void executed(boolean success, MessageReceivedEvent e)
	{
		
	}

}
