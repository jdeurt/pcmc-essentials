package com.sharky.pcmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.sharky.pcmc.PCMCEssentials;

public class CommandPCMC implements CommandExecutor {
	private PCMCEssentials plugin;
	
	public CommandPCMC(PCMCEssentials plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("pcmcessentials.admin")) {
			sender.sendMessage("Missing permission pcmcessentials.admin");
			
			return true;
		}
		
		if (args.length != 1) {
			sender.sendMessage("Invalid syntax");
			
			return false;
		}
		
		if (args[0].equals("reload")) {
			this.plugin.reloadConfig();
			
			sender.sendMessage("Reloaded config");
			
			return true;
		} else if (args[0].equals("info")) {
			sender.sendMessage("PCMCEssentials plugin v" + this.plugin.getDescription().getVersion() + " developed by sh_rky");
			
			return true;
		} else {
			sender.sendMessage("Invalid syntax");
			
			return false;
		}
	}
}
