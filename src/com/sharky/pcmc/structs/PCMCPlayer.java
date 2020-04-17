package com.sharky.pcmc.structs;

import java.lang.ref.WeakReference;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PCMCPlayer {
	private WeakReference<Player> player;
	private final UUID uuid;
	private Settings globalSettings;
	
	// Lava griefing

	private int concurrentLavaPlaced = 0;
	private long lastLavaPlacedTimestamp = 0;
	
	public PCMCPlayer(final Player player, Settings globalSettings) {
		this.player = new WeakReference<>(player);
		this.uuid = player.getUniqueId();
		this.globalSettings = globalSettings;
	}
	
	public void lavaPlaced() {
		long currentTime = System.currentTimeMillis();

		if (currentTime - this.lastLavaPlacedTimestamp < this.globalSettings.getConcurrentLavaTimeout()) {
			this.concurrentLavaPlaced += 1;
		} else {
			this.concurrentLavaPlaced = 1;
		}

		this.lastLavaPlacedTimestamp = System.currentTimeMillis();
	}
	
	public long getLastLavaPlacedTimestamp() {
		return this.lastLavaPlacedTimestamp;
	}
	
	public int getConcurrentLavaPlaced() {
		return this.concurrentLavaPlaced;
	}
	
	public final Player getPlayer() {
		return player.get();
	}

	protected void setPlayer(final Player player) {
		this.player = new WeakReference<>(player);
	}

	public final String getName() {
		return getPlayer().getName();
	}

	public final UUID getUUID() {
		return uuid;
	}

	public final boolean isOnline() {
		return getPlayer() != null;
	}

	public final void message(final String message) {
		if (isOnline() && !message.isEmpty()) {
			getPlayer().sendMessage(message);
		}
	}
}
