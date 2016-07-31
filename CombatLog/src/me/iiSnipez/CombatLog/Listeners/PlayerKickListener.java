package me.iiSnipez.CombatLog.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import me.iiSnipez.CombatLog.CombatLog;

public class PlayerKickListener implements Listener {

	CombatLog plugin;

	public PlayerKickListener(CombatLog plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onKickEvent(PlayerKickEvent event) {
		Player player = event.getPlayer();
		if (plugin.taggedPlayers.containsKey(player.getName()) && plugin.removeTagOnKick
				&& !event.getReason().toLowerCase().contains("spam")) {
			plugin.taggedPlayers.remove(player.getName());
		}
	}
}
