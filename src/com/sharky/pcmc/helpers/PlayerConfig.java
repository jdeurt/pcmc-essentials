package com.sharky.pcmc.helpers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;

public class PlayerConfig {
    private FileConfiguration config;
    private File playerFile;
    private Player player;
    private JavaPlugin plugin;

    public PlayerConfig(JavaPlugin plugin, Player player) {
        File folder = new File(plugin.getDataFolder() + File.separator + "players");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String playerId = player.getUniqueId().toString();

        this.playerFile = new File(folder.getAbsolutePath() + File.separator + playerId + ".yml");

        if (!this.playerFile.exists()) {
            try {
                this.playerFile.createNewFile();
            } catch (IOException exception) {
                plugin.getLogger().warning("Exception encountered while trying to create player config file!");
            }
        }

        this.config = YamlConfiguration.loadConfiguration(this.playerFile);
        this.player = player;
        this.plugin = plugin;
    }
    
    public void setQuadStatus(boolean status) {
    	this.config.set("player.isInQuad", status);
    }
    
    public boolean getQuadStatus() {
    	if (!this.config.contains("player.isInQuad")) {
    		return false;
    	}

    	return this.config.getBoolean("player.isInQuad");
    }

    public Player getPlayer() {
        return this.player;
    }
    
    public void save() {
    	try {
    		this.config.save(this.playerFile);
    	} catch (IOException exception) {
    		this.plugin.getLogger().warning("Exception encountered while trying to save player config file!");
    	}
    }
}