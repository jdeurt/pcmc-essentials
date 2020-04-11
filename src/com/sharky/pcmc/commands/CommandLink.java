package com.sharky.pcmc.commands;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpResponse;
import org.bukkit.command.Command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandLink implements CommandExecutor {
	private JavaPlugin plugin;
	
	public CommandLink(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (args.length != 1) {
				player.sendMessage("Incorrect command syntax");
				
				return false;
			}
			
			String discordId = args[0];
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
			
			HttpPost postRequest = new HttpPost("https://api.pcmc.xyz/link/" + player.getName() + "/" + discordId);
			
			HttpResponse response;
			
			try {
				response = httpClient.execute(postRequest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return false;
			}
			
			

			return true;
		} else {
			this.plugin.getLogger().info("You must be a player to execute this command");
			
			return true;
		}
	}
}
