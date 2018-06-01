package me.NinetyNine.totd.utils;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TOTDUtils implements Listener {

	public static void sendPlayerMessage(Player player, String message) {
		String prefix = "&7[&bTipOfTheDay&7] ";
		player.sendMessage(translateAlternateColorCodes('&', prefix + message));
	}

	public static void sendConsoleMessage(String message) {
		Bukkit.getServer().getLogger().info("[TipOfTheDay] " + message);
	}
}