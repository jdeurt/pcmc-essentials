package com.sharky.pcmc.completers;

import java.util.List;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CompleterPCMC implements TabCompleter {
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1) {
			List<String> validArgsList = Arrays.asList("reload", "info");
			
			return validArgsList;
		}
		
		return null;
	}
}
