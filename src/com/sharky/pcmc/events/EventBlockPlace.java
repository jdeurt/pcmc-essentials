package com.sharky.pcmc.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.sharky.pcmc.PCMCEssentials;
import com.sharky.pcmc.structs.PCMCPlayer;

import java.util.Iterator;

import org.bukkit.Material;

public class EventBlockPlace implements Listener {
	private PCMCEssentials plugin;

	public EventBlockPlace(PCMCEssentials plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.getBlockPlaced().getType() == Material.LAVA) {
			PCMCPlayer pcmcPlayer = new PCMCPlayer(event.getPlayer(), this.plugin.globalSettings);
			
			this.plugin.addCachedPlayer(pcmcPlayer);
			
			synchronized (this.plugin.players) {
				final Iterator<PCMCPlayer> iterator = this.plugin.players.iterator();
				boolean didFindPlayer = false;

				while (iterator.hasNext() && !didFindPlayer) {
					final PCMCPlayer p = iterator.next();
					
					if (p.getUUID().equals(event.getPlayer().getUniqueId())) {
						didFindPlayer = true;
						
						p.lavaPlaced();
						
						this.plugin.getLogger().info("Player " + p.getName() + " placed " + p.getConcurrentLavaPlaced() + "lava blocks in a row");
					}
				}
			}
		}
	}
}
