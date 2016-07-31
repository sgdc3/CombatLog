package me.iiSnipez.CombatLog.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import me.iiSnipez.CombatLog.CombatLog;

public class PlayerTeleportListener implements Listener {

	CombatLog plugin;

	public PlayerTeleportListener(CombatLog plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		Player player = event.getPlayer();
		if (plugin.taggedPlayers.containsKey(player.getName())) {
			if (plugin.blockTeleportationEnabled) {
				if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT)
						|| event.getCause().equals(PlayerTeleportEvent.TeleportCause.COMMAND)
						|| event.getCause().equals(PlayerTeleportEvent.TeleportCause.END_GATEWAY)
						|| event.getCause().equals(PlayerTeleportEvent.TeleportCause.END_PORTAL)
						|| event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)
						|| event.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL)
						|| event.getCause().equals(PlayerTeleportEvent.TeleportCause.PLUGIN)
						|| event.getCause().equals(PlayerTeleportEvent.TeleportCause.SPECTATE)
						|| event.getCause().equals(PlayerTeleportEvent.TeleportCause.UNKNOWN)) {
					event.setCancelled(true);
					if (plugin.blockTeleportationMessageEnabled) {
						player.sendMessage(plugin.translateText(plugin.blockTeleportationMessage));
					}

				}
			} else if (plugin.blockEnderpearlsEnabled) {
				if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
					event.setCancelled(true);
					if (plugin.blockTeleportationMessageEnabled) {
						player.sendMessage(plugin.translateText(plugin.blockTeleportationMessage));
					}
				}
			}
		}
	}

}
