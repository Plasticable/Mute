package ru.failer.votemute.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandListener implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String text, String[] arg3) {

		if (cmd.getName().equals("vmute")) {
			VoteHandler.startVote(arg3[0], Bukkit.getPlayer(sender.getName()));
			return true;
		}
		if (cmd.getName().equals("vote")) {
			VoteHandler.Vote(arg3[0], Bukkit.getPlayer(sender.getName()));
			return true;
		}
		return false;
	}

}
