package me.iiSnipez.CombatLog.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.iiSnipez.CombatLog.CombatLog;

public class PlayerJoinListener implements Listener {

	CombatLog plugin;

	public PlayerJoinListener(CombatLog plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (plugin.updateCheckEnabled && player.hasPermission("combatlog.update") && plugin.updateAvaliable) {
			if (plugin.updateCheckMessageEnabled) {
				player.sendMessage(plugin.translateText(plugin.updateCheckMessage));
			}
		}
		if(plugin.MOTDEnabled && plugin.MOTDMessageEnabled){
			player.sendMessage(plugin.translateText(plugin.MOTDMessage));
		}
		if(plugin.killMessageEnabled && plugin.killPlayers.contains(player.getUniqueId().toString()))
			player.sendMessage(plugin.translateText(plugin.killMessage));
	}
}
