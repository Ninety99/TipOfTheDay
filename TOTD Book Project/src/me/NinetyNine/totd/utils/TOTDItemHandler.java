package me.NinetyNine.totd.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import me.NinetyNine.totd.TOTD;

public class TOTDItemHandler implements Listener {

	public static void giveBook(Player player, ItemStack old) {
		List<String> pages = new ArrayList<String>();
		
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);

		String gcStaff = "&8- GuildCraft Staff";
		String string = "&b-=-=-=-=-=-=-=-=-\n" + "&r", string2 = "&3Tip Of The Day:\n", spaces = "\n";

		String fromConfig = TOTD.plugin.getConfig().getString("totd");

		pages.add(ChatColor.translateAlternateColorCodes('&',
				string + string2 + string + fromConfig + spaces + string + spaces + gcStaff));

		BookMeta meta = (BookMeta) book.getItemMeta();
		meta.setPages(pages);
		meta.setDisplayName(ChatColor.GREEN + "Tip of the day");
		meta.setAuthor(ChatColor.AQUA + "GuildCraft Staff");
		book.setItemMeta(meta);

		player.getInventory().setItemInHand(book);
	}

	public static void giveItemBack(Player player, ItemStack old) {
		player.getInventory().setItemInHand(old);
	}
}
