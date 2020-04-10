package com.sharky.pcmc;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public class PCMCEssentials extends JavaPlugin implements Listener {
	FileConfiguration config = getConfig();
	
	@Override
	public void onEnable() {
		config.addDefault("allow-quad-reselect", false);
		config.options().copyDefaults(true);
		saveConfig();
		
		getServer().getPluginManager().registerEvents(this, this);
		
		getLogger().info("Enabled PCMC Essentials");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Disabled PCMC Essentials");
	}
}
