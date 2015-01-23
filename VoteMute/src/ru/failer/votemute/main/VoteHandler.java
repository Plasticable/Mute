package ru.failer.votemute.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class VoteHandler {
	static int truevote;
	static int falsevote;
	public static boolean vote;
	static String plVoteName;
	static String plName;
	static ArrayList<String> playersvote;
	public static Player player;
	static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("VoteMute");
	public static boolean cd = false;
	public static void startVote(String pName, Player psName) {
		if(cd){
			psName.sendMessage("[VOTEMSG] Перерыв между командами еще не прошел!");
			return;
		}
		player = psName;
		boolean t = false;
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getName().equals(pName)) {
				t = true;

			}

		}
		if (t == true) {
			if (Mute.checkMute(pName) == true) {

				psName.sendMessage("[VOTEMSG] Этот игрок уже в муте!");
				return;
			}
			plName = pName;
			plVoteName = psName.getName();
			if (vote == true) {
				psName.sendMessage("[VOTEMSG] Голосование уже идет!");
				return;
			} else {
				vote = true;

				playersvote = new ArrayList<String>();
				truevote = 0;
				falsevote = 0;
				Bukkit.getServer().broadcastMessage("Началось голосование за мут игрока "+pName);
				Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

					public void run() {
						endVote();
					}

				}, Main.vote * 20);
			}
		} else {
			psName.sendMessage("[VOTEMSG] Такого игрока нет на сервере!");
		}
	}

	public static void Vote(String votestr,Player pl) {
		
		boolean t = false;
		if (vote == true) {
			if(pl.equals(player)) {
				pl.sendMessage("[VOTEMSG] Вы начали голосование, вы не можете голосовать!");
				return;
			}
			if (playersvote.size() != 0) {
				for (int i = 0; i < playersvote.size(); i++) {
					if (playersvote.get(i).equals(plVoteName)) {
						t = true;
						Bukkit.getPlayer(plVoteName).sendMessage("[VOTEMSG] Вы уже проголосовали!");
					}
				}
				if (t != true) {
					if (votestr.equals("yes")) {
						truevote++;
						playersvote.add(plVoteName);
					}
					if (votestr.equals("no")) {
						falsevote++;
						playersvote.add(plVoteName);
					}
				}
			} else {
				if (votestr.equals("yes")) {
					truevote++;
					playersvote.add(plVoteName);

				}
				if (votestr.equals("no")) {
					falsevote++;
					playersvote.add(plVoteName);
				}
			}
		} else {

		}

	}

	private static void endVote() {
		vote = false;
		if (truevote == falsevote) {
			Bukkit.getServer().broadcastMessage("[VOTEMSG] Голоса распределились поравну!");
		}
		if (truevote > falsevote) {
			Bukkit.getServer().broadcastMessage("[VOTEMSG] Голосов за больше! " + plName + " будет замучен!");
			Mute.addMute(plName);
		}
		if (truevote < falsevote) {
			Bukkit.getServer().broadcastMessage("[VOTEMSG] Голосов против больше! " + plName + " не будет замучен!");
		}
		playersvote = null;
		cd = true;
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

			public void run() {
				cd = false;
			}

		}, Main.cd * 20);
	
	}

}