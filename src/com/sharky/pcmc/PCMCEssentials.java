package com.sharky.pcmc;

import org.bukkit.plugin.java.JavaPlugin;

import com.sharky.pcmc.commands.*;
import com.sharky.pcmc.completers.*;
import com.sharky.pcmc.events.*;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public class PCMCEssentials extends JavaPlugin implements Listener {
	public FileConfiguration config = getConfig();
	
	@Override
	public void onEnable() {
		// Config stuff
		saveDefaultConfig();
		getConfig().addDefault("allow-quad-reselect", false);
		config.options().copyDefaults(true);
		saveConfig();
		
		// Register listeners
		getServer().getPluginManager().registerEvents(new EventPlayerTeleport(), this);
		
		// Register commands
		this.getCommand("pcmcessentialsreload").setExecutor(new CommandReload(this));
		this.getCommand("quad").setExecutor(new CommandQuad(this));
		this.getCommand("quad").setTabCompleter(new CompleterQuad());
		// this.getCommand("link").setExecutor(new CommandLink(this));
		
		getLogger().info("Enabled PCMC Essentials");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Disabled PCMC Essentials");
	}
}
