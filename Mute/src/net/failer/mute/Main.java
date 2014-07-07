package net.failer.mute;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin implements Listener {
	Plugin plugin = this;
	String votenameplayer;
	boolean cooldown;
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		System.out.println("S.T.A.L.K.E.R by Failer activated!");
		Bukkit.getPluginManager().registerEvents(this, this);
		getCommand("vote").setExecutor(new vote() );
		getCommand("votemute").setExecutor(new votemute() );
	}
	public class vote implements CommandExecutor
	{
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			
			if(votenameplayer != null)
			{
				
			
				
			if(args.length<1)
			{
				return false;
			}
			if(args[0] == "1" || args[0] == "2" )
			{
				//metod(args[0],sender)
			}
			else
			{
				return false;
			}
			}
			else
			{
				sender.sendMessage("Голосование сейчас не проходит!");
			}
			return true;
		}
		
	}
	public class votemute implements CommandExecutor
	{
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(cooldown == false)
			{
	
			if(args.length<1)
			{
				return false;
			}
			Player player = Bukkit.getServer().getPlayerExact(args[0]);
			if(player != null)
			{
				//метод голосования
			}
			else
			{
				sender.sendMessage("Такой игрок не найден!");
			}
			}
			else
			{
				
			}
			
			return true;
		}
		
	}

}
