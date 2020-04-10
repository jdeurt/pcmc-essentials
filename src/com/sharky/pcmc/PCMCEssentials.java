package com.sharky.pcmc;

import org.bukkit.plugin.java.JavaPlugin;

import com.sharky.pcmc.commands.*;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public class PCMCEssentials extends JavaPlugin implements Listener {
	FileConfiguration config = getConfig();
	
	@Override
	public void onEnable() {
		// Config stuff
		config.addDefault("allow-quad-reselect", false);
		config.options().copyDefaults(true);
		saveConfig();
		
		// Register listeners
		getServer().getPluginManager().registerEvents(this, this);
		
		// Register commands
		this.getCommand("quad").setExecutor(new CommandQuad(this));
		this.getCommand("link").setExecutor(new CommandLink());
		
		getLogger().info("Enabled PCMC Essentials");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Disabled PCMC Essentials");
	}
}
