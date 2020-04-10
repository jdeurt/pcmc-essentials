package com.sharky.pcmc.completers;

import java.util.List;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class CompleterQuad implements TabCompleter {
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (sender instanceof Player) {
			if (args.length == 1) {
				List<String> quadsList = Arrays.asList("authleft", "authright", "libleft", "libright");
				
				return quadsList;
			}
		}
		
		return null;
	}
}
