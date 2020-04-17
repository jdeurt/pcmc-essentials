package com.sharky.pcmc.events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.sharky.pcmc.PCMCEssentials;

public class EventPlayerChat implements Listener {
	private PCMCEssentials plugin;
	
	public EventPlayerChat(PCMCEssentials plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		if (!this.plugin.config.getBoolean("api.send-chat-events")) {
			return;
		}

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost postRequest = new HttpPost("https://api.pcmc.xyz/mcchat/" + event.getPlayer().getDisplayName());
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("token", this.plugin.config.getString("api.token")));
		params.add(new BasicNameValuePair("message", event.getMessage()));
		
		try {
			httpClient.execute(postRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
