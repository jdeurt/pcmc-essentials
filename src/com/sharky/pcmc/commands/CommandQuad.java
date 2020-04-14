package com.sharky.pcmc.commands;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sharky.pcmc.PCMCEssentials;
import com.sharky.pcmc.helpers.PlayerConfig;

public class CommandQuad implements CommandExecutor {
	private PCMCEssentials plugin;
	
	public CommandQuad(PCMCEssentials plugin) {
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
			Player target = (Player) sender;
			
			if (args.length != 1 && args.length != 2) {
				player.sendMessage("Incorrect command syntax");
				
				return false;
			}
			
			if (args.length == 2) {
				if (!player.hasPermission("pcmcessentials.admin")) {
					player.sendMessage("Missing permission pcmcessentials.admin");
					
					return true;
				}
				
				target = this.plugin.getServer().getPlayerExact(args[1]);
				
				if (target == null) {
					player.sendMessage("Player not found");
					
					return false;
				}
			}
			
			PlayerConfig playerConfig = new PlayerConfig(this.plugin, player);
			
			if (!this.plugin.config.getBoolean("allow-quad-reselect") && playerConfig.getQuadStatus() && !player.hasPermission("pcmcessentials.admin")) {
				player.sendMessage("You cannot change your quad once it has been set");
				
				return true;
			}

			if (args[0].equalsIgnoreCase("authleft")) {
				this.setPlayerNameColor(target, "&c");
			} else if (args[0].equalsIgnoreCase("authright")) {
				this.setPlayerNameColor(target, "&9");
			} else if (args[0].equalsIgnoreCase("libleft")) {
				this.setPlayerNameColor(target, "&a");
			} else if (args[0].equalsIgnoreCase("libright")) {
				this.setPlayerNameColor(target, "&e");
			} else {
				player.sendMessage("Invalid quadrant name provided");
				
				return false;
			}
			
			playerConfig.setQuadStatus(true);
			playerConfig.save();
			
			player.sendMessage(target.getName() + "'s quad is now " + args[0]);
			
			return true;
		} else {
			this.plugin.getLogger().info("You must be a player to execute this command");
			
			return true;
		}
	}
}
