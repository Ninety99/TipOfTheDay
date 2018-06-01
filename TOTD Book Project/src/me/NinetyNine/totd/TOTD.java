package me.NinetyNine.totd;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.NinetyNine.totd.eventhandler.TOTDJoinHandler;
import me.NinetyNine.totd.eventhandler.TOTDQuitHandler;
import me.NinetyNine.totd.utils.TOTDUtils;

public class TOTD extends JavaPlugin {

	public static TOTD plugin;

	@Override
	public void onEnable() {
		plugin = this;

		registerListeners();
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
	}
}