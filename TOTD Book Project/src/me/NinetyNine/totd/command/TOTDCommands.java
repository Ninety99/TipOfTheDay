package me.NinetyNine.totd.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.totd.TOTD;
import me.NinetyNine.totd.utils.TOTDBooleanHandler;
import me.NinetyNine.totd.utils.TOTDUtils;

public class TOTDCommands implements Listener, CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You need to be a player in order to execute this command!");
			return true;
		} else {
			Player player = (Player) sender;
			ItemStack old = player.getInventory().getItemInHand();

			if (cmd.getName().equalsIgnoreCase("totd")) {
				if (args.length == 0) {
					TOTDUtils.openBook(player, old);
					return true;
				}
			}

			if (cmd.getName().equalsIgnoreCase("gctotd")) {
				if (player.hasPermission("totd.admin")) {
					if (args.length == 0) {
						sendHelpMessage(player);
						return true;
					}

					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("open")) {
							for (Player players : Bukkit.getServer().getOnlinePlayers()) {
								ItemStack oldAll = players.getInventory().getItemInHand();
								TOTDUtils.openBook(players, oldAll);
							}
							return true;
						}

						if (args[0].equalsIgnoreCase("toggle")) {
							if (TOTDBooleanHandler.getOpenBook() == true) {
								TOTDBooleanHandler.setOpenBook(false);
								TOTDUtils.sendPlayerMessage(player, "&2Succesfully set to false!");
								return true;
							}
							if (TOTDBooleanHandler.getOpenBook() == false) {
								TOTDBooleanHandler.setOpenBook(true);
								TOTDUtils.sendPlayerMessage(player, "&2Succesfully set to true!");
								return true;
							}
						}

						if (args[0].equalsIgnoreCase("reload")) {
							saveConfig();
							TOTDUtils.sendPlayerMessage(player, "&cReloaded.");
							return true;
						}
					}

					if (args[0].equalsIgnoreCase("set")) {
						if (args.length == 1) {
							TOTDUtils.sendPlayerMessage(player, "&cUsage: /gctotd set <message>");
							return true;
						}
						String message = "";
						for (int i = 1; i < args.length; i++) {
							message += args[i] + " ";
						}
						message.trim();
						TOTDUtils.setTOTDMessage(message);
						saveConfig();
						TOTDUtils.sendPlayerMessage(player, "&cSuccesfully set!");
						return true;
					}
				} else {
					TOTDUtils.sendPlayerMessage(player, "&cYou do not have permissions to execute this command!");
					return true;
				}
			}
		}
		return true;
	}

	public void sendHelpMessage(Player player) {
		TOTDUtils.sendPlayerMessage(player, "&cCommands:\n"
				+ "&b/gctotd open &7> &8Opens the TOTD book for all players,\n"
				+ "&b/gctotd toggle &7> &8Set to true or false if should keep opening books after joining. &3(Default: true)\n"
				+ "&b/gctotd reload &7> &8Reload config.\n" + "&b/gctotd set <message> &7> &8Sets the TOTD message.");
	}
	
	public void saveConfig() {
		String before = TOTD.plugin.getConfig().getString("totd");
		TOTD.plugin.getConfig().set("totd", before);
		TOTD.plugin.saveConfig();
	}
}