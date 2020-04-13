package com.sharky.pcmc.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.SirBlobman.combatlogx.api.ICombatLogX;
import com.SirBlobman.combatlogx.api.utility.ICombatManager;

public class EventPlayerTeleport implements Listener {
	public boolean isInCombat(Player player) {
	    // Make sure to check that CombatLogX is enabled before using it for anything.
	    ICombatLogX plugin = (ICombatLogX) Bukkit.getPluginManager().getPlugin("CombatLogX");
	    ICombatManager combatManager = plugin.getCombatManager();
	    return combatManager.isInCombat(player);
	}

	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		boolean isCombatTagged = this.isInCombat(event.getPlayer());
		
		if (isCombatTagged) {
			if (event.getCause() == PlayerTeleportEvent.TeleportCause.COMMAND || event.getCause() == PlayerTeleportEvent.TeleportCause.PLUGIN) {
				event.setCancelled(true);
			}
		}
	}
}
