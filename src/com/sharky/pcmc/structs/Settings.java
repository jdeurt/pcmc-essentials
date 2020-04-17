package com.sharky.pcmc.structs;

import com.sharky.pcmc.PCMCEssentials;

public class Settings {
	// Quad
	private boolean quadAllowReselect;

	// API
	private String apiToken;
	private boolean apiSendChatEvents;

	// Lava griefing
	private long concurrentLavaTimeout;
	private int concurrentLavaWarningAmount;
	private int concurrentLavaAutokickAmount;
	
	public void update(PCMCEssentials plugin) {
		this.quadAllowReselect = plugin.getConfig().getBoolean("quad.allow-reselect");

		this.apiToken = plugin.getConfig().getString("api.token");
		this.apiSendChatEvents = plugin.getConfig().getBoolean("api.send-chat-events");
		
		this.concurrentLavaTimeout = plugin.getConfig().getLong("antigrief.concurrent-lava-timeout");
		this.concurrentLavaWarningAmount = plugin.getConfig().getInt("antigrief.concurrent-lava-warning-amount");
		this.concurrentLavaAutokickAmount = plugin.getConfig().getInt("antigrief.concurrent-lava-autokick-amount");
	}
	
	public boolean getQuadAllowReselect() {
		return this.quadAllowReselect;
	}
	
	public String getApiToken() {
		return this.apiToken;
	}
	
	public boolean getApiSendChatEvents() {
		return this.apiSendChatEvents;
	}
	
	public long getConcurrentLavaTimeout() {
		return this.concurrentLavaTimeout;
	}
	
	public int getConcurrentLavaWarningAmount() {
		return this.concurrentLavaWarningAmount;
	}
	
	public int getConcurrentLavaAutokickAmount() {
		return this.concurrentLavaAutokickAmount;
	}
}
