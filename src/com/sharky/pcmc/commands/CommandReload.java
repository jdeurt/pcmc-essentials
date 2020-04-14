package com.sharky.pcmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.sharky.pcmc.PCMCEssentials;

public class CommandReload implements CommandExecutor {
	private PCMCEssentials plugin;
	
	public CommandReload(PCMCEssentials plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("pcmcessentials.admin")) {
			sender.sendMessage("Missing permission pcmcessentials.admin");
			
			return true;
		}
		
		this.plugin.reloadConfig();
		
		sender.sendMessage("Reloaded config");
		
		return true;
	}
}
