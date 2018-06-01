package me.NinetyNine.totd.eventhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.utility.MinecraftReflection;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.NinetyNine.totd.TOTD;
import me.NinetyNine.totd.utils.TOTDUtils;

public class TOTDJoinHandler implements Listener {

	public ItemStack book = new ItemStack(Material.WRITTEN_BOOK);

	public static List<UUID> players = new ArrayList<UUID>();

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		ItemStack old = player.getInventory().getItemInHand();
		World kitpvpWorld = Bukkit.getWorld(""); // KitPvP World, just put the kitpvp world name inside the quotes (")
		World practicepvpWorld = Bukkit.getWorld(""); // PracticePvP World, just put the practicepvp world name inside the quotes (")

		if (player.getWorld() == kitpvpWorld || player.getWorld() == practicepvpWorld) {
			if (!(players.contains(player.getUniqueId()))) {
				players.add(player.getUniqueId());
				giveBook(player, old);
				new BukkitRunnable() {
					@Override
					public void run() {
						sendPacket(player);
						giveItemBack(player, old);
						TOTDUtils.sendPlayerMessage(player, "&2You have recieved the Tip Of The Day book!");
					}
				}.runTaskLater(TOTD.plugin, 2L);
				return;
			} else
				return;
		} else
			return;
	}

	public void giveBook(Player player, ItemStack old) {
		List<String> pages = new ArrayList<String>();
		String hackusating = "&cDo not hackusate(accusing a player of hacking)! This will lead to a 6 hour mute. To report the specific player, use /report <player> <reason>.";
		String string = "&b-=-=-=-=-=-=-=-=-\n", string2 = "&3Tip Of The Day:\n", spaces = "\n";

		pages.add(ChatColor.translateAlternateColorCodes('&',
				string + string2 + string + "&c" + hackusating + spaces + string + spaces + "&8- GuildCraft Staff"));

		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setPages(pages);
		meta.setDisplayName(ChatColor.GREEN + "Tip of the day");
		meta.setAuthor(ChatColor.AQUA + "GuildCraft Staff");
		book.setItemMeta(meta);

		player.getInventory().setItemInHand(book);
	}

	public void giveItemBack(Player player, ItemStack old) {
		player.getInventory().setItemInHand(old);
		player.getInventory().addItem(book);
	}

	public void sendPacket(Player player) {
		try {
			PacketContainer pc = ProtocolLibrary.getProtocolManager()
					.createPacket(PacketType.Play.Server.CUSTOM_PAYLOAD);
			pc.getModifier().writeDefaults();
			ByteBuf bf = Unpooled.buffer(256);
			bf.setByte(0, (byte) 0);
			bf.writerIndex(1);
			pc.getModifier().write(1, MinecraftReflection.getPacketDataSerializer(bf));
			pc.getStrings().write(0, "MC|BOpen");
			ProtocolLibrary.getProtocolManager().sendServerPacket(player, pc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
