package me.NinetyNine.totd.eventhandler;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class TOTDQuitHandler implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		if (TOTDJoinHandler.players.contains(player.getUniqueId()))
			return;
		else
			return;
	}
}
