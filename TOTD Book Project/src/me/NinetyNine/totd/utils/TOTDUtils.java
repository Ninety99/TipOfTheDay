package me.NinetyNine.totd.utils;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.NinetyNine.totd.TOTD;

public class TOTDUtils implements Listener {

	public static void sendPlayerMessage(Player player, String message) {
		String prefix = "&7[&bTipOfTheDay&7] ";
		player.sendMessage(translateAlternateColorCodes('&', prefix + message));
	}

	public static void sendConsoleMessage(String message) {
		Bukkit.getServer().getLogger().info("[TipOfTheDay] " + message);
	}
	
	public static void openBook(Player player, ItemStack old) {
		TOTDItemHandler.giveBook(player, old);
		new BukkitRunnable() {
			@Override
			public void run() {
				TOTDPacketHandler.sendPacket(player);
				TOTDItemHandler.giveItemBack(player, old);
				TOTDUtils.sendPlayerMessage(player, "&2You have recieved the Tip Of The Day book!");
			}
		}.runTaskLater(TOTD.plugin, 2L);
	}
	
	public static void setTOTDMessage(String message) {
		TOTD.plugin.getConfig().set("totd", message);
	}
}