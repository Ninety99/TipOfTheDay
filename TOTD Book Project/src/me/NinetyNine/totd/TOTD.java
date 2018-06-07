package me.NinetyNine.totd;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.NinetyNine.totd.command.TOTDCommands;
import me.NinetyNine.totd.eventhandler.TOTDJoinHandler;
import me.NinetyNine.totd.eventhandler.TOTDQuitHandler;
import me.NinetyNine.totd.utils.TOTDBooleanHandler;
import me.NinetyNine.totd.utils.TOTDItemHandler;
import me.NinetyNine.totd.utils.TOTDPacketHandler;
import me.NinetyNine.totd.utils.TOTDUtils;

public class TOTD extends JavaPlugin {

	public static TOTD plugin;

	@Override
	public void onEnable() {
		plugin = this;

		registerListeners();
		registerStatic();
		registerCommands();
		loadConfig();
		TOTDUtils.sendConsoleMessage("Enabled!");
	}

	@Override
	public void onDisable() {
		TOTDJoinHandler.players.clear();
		TOTDUtils.sendConsoleMessage("Disabled!");
	}

	private void registerListeners() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new TOTDJoinHandler(), this);
		pm.registerEvents(new TOTDQuitHandler(), this);
		pm.registerEvents(new TOTDUtils(), this);
		pm.registerEvents(new TOTDCommands(), this);
		pm.registerEvents(new TOTDItemHandler(), this);
		pm.registerEvents(new TOTDPacketHandler(), this);
		pm.registerEvents(new TOTDBooleanHandler(), this);
	}
	
	private void registerStatic() {
		TOTDJoinHandler.players = new ArrayList<UUID>();
	}
	
	private void registerCommands() {
		getCommand("totd").setExecutor(new TOTDCommands());
		getCommand("gctotd").setExecutor(new TOTDCommands());
	}
	
	private void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}