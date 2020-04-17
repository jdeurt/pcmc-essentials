package com.sharky.pcmc;

import org.bukkit.plugin.java.JavaPlugin;

import com.sharky.pcmc.commands.*;
import com.sharky.pcmc.completers.*;
import com.sharky.pcmc.events.*;
import com.sharky.pcmc.structs.PCMCPlayer;
import com.sharky.pcmc.structs.Settings;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public class PCMCEssentials extends JavaPlugin implements Listener {
	public final Set<PCMCPlayer> players = Collections.synchronizedSet(new HashSet<PCMCPlayer>());

	public Settings globalSettings;

	public FileConfiguration config = getConfig();
	
	public final void addCachedPlayer(PCMCPlayer p) {
		this.players.add(p);
	}
	
	public final void removeCachedPlayer(PCMCPlayer p) {
		this.players.remove(p);
	}
	
	@Override
	public void onEnable() {
		// Config stuff
		saveDefaultConfig();
		getConfig().addDefault("quad.allow-reselect", false);
		getConfig().addDefault("api.token", "CHANGE_THIS");
		getConfig().addDefault("api.send-chat-events", false);
		getConfig().addDefault("antigrief.concurrent-lava-timeout", 10000);
		getConfig().addDefault("antigrief.concurrent-lava-warning-amount", 10);
		getConfig().addDefault("antigrief.concurrent-lava-warning-amount", -1);
		config.options().copyDefaults(true);
		saveConfig();
		
		globalSettings.update(this);
		
		// Register listeners
		getServer().getPluginManager().registerEvents(new EventPlayerTeleport(), this);
		// getServer().getPluginManager().registerEvents(new EventPlayerChat(this), this);
		getServer().getPluginManager().registerEvents(new EventBlockPlace(this), this);
		
		// Register commands
		this.getCommand("pcmc").setExecutor(new CommandPCMC(this));
		this.getCommand("pcmc").setTabCompleter(new CompleterPCMC());
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
