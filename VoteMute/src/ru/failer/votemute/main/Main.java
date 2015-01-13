package ru.failer.votemute.main;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("deprecation")
public class Main extends JavaPlugin implements Listener {
	public static int vote;
	public static int mute;

	public void onEnable() {
		this.saveDefaultConfig();
		CommandListener listener = new CommandListener();
		getCommand("vmute").setExecutor(listener);
		getCommand("vote").setExecutor(listener);
		System.out.println("VoteMute by Failer activated!");
		Bukkit.getPluginManager().registerEvents(this, this);

		mute = this.getConfig().getInt("votemute.time_mute");
		vote = this.getConfig().getInt("votemute.time_vote");
	}

	@EventHandler
	public void onsend(PlayerChatEvent event) {
		if (Mute.checkMute(event.getPlayer().getName()) == true) {
			event.setCancelled(true);
			event.getPlayer().sendMessage("[VOTEMSG] Вы не можете писать в чат!");
		}
	}

}