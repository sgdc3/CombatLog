package me.iiSnipez.CombatLog.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.iiSnipez.CombatLog.CombatLog;

public class PlayerCommandPreprocessListener implements Listener {

	public CombatLog plugin;

	public PlayerCommandPreprocessListener(CombatLog plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String cmd = event.getMessage();
		if (plugin.blockCommandsEnabled && plugin.taggedPlayers.containsKey(player.getName())
				&& !cmd.equalsIgnoreCase("ct") && !cmd.equalsIgnoreCase("tag")) {
			if (plugin.blockCommandNames.contains("*")) {
				event.setCancelled(true);
				if (plugin.blockCommandsMessageEnabled) {
					player.sendMessage(plugin.translateText(plugin.blockCommandsMessage + ""));
					return;
				}
			}
			for (String s : plugin.blockCommandNames) {
				if (cmd.toLowerCase().contains("/" + s.toLowerCase())) {
					event.setCancelled(true);
					if (plugin.blockCommandsMessageEnabled) {
						player.sendMessage(plugin.translateText(plugin.blockCommandsMessage + ""));
					}
				}
			}
		}
	}
}
