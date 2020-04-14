package com.sharky.pcmc.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class EventPlayerTeleport implements Listener {
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		boolean canTP = event.getPlayer().hasPermission("pcmcessentials.allowtp");
		
		if (!canTP) {
			if (event.getCause() == PlayerTeleportEvent.TeleportCause.COMMAND || event.getCause() == PlayerTeleportEvent.TeleportCause.PLUGIN || event.getCause() == PlayerTeleportEvent.TeleportCause.COMMAND || event.getCause() == PlayerTeleportEvent.TeleportCause.UNKNOWN) {
				event.setCancelled(true);
			}
		}
	}
}
