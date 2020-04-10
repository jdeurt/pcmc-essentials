package com.sharky.pcmc.commands;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandQuad implements CommandExecutor {
	private JavaPlugin plugin;
	
	public CommandQuad(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	private void setPlayerNameColor(Player player, String color) {
		String oldName = player.getName();
		Server server = this.plugin.getServer();
		
		server.dispatchCommand(server.getConsoleSender(), "nick " + oldName + " " + color + oldName);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
		}
		
		return true;
	}
}
