package me.NinetyNine.totd.eventhandler;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.totd.utils.TOTDBooleanHandler;
import me.NinetyNine.totd.utils.TOTDUtils;

public class TOTDJoinHandler implements Listener {

	public static List<UUID> players;

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (TOTDBooleanHandler.getOpenBook() == true) {
			Player player = e.getPlayer();
			ItemStack old = player.getInventory().getItemInHand();
			World world = Bukkit.getWorld("world");

			if (player.getWorld() == world) {
				if (!(players.contains(player.getUniqueId()))) {
					players.add(player.getUniqueId());
					TOTDUtils.openBook(player, old);
					return;
				} else
					return;
			} else
				return;
		} else
			return;
	}
}