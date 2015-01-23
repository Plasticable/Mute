package ru.failer.votemute.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Mute {
	static ArrayList<String> players = new ArrayList<String>();
	static String pname;

	public static void addMute(String name) {
		pname = name;
		players.add(name);
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("VoteMute");
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

			public void run() {
				removeMute(pname);
			}

		}, Main.mute * 20);
	}

	public static void removeMute(String name) {
		for (int i = 0; i <= players.size(); i++) {
			if (players.get(i).equals(name)) {
				players.remove(i);
			}
		}
	}

	public static boolean checkMute(String name) {
		if (players.size() != 0) {
			for (int i = 0; i < players.size(); i++) {
				System.out.print(players.get(i));
				System.out.print(name);
				if (players.get(i).equals(name)) {
					return true;
				}
			}
		}

		return false;
	}
}
