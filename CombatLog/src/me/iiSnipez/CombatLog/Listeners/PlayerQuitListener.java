package me.iiSnipez.CombatLog.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.iiSnipez.CombatLog.CombatLog;
import me.iiSnipez.CombatLog.Events.PlayerCombatLogEvent;

public class PlayerQuitListener implements Listener {

	CombatLog plugin;

	public PlayerQuitListener(CombatLog plugin) {
		this.plugin = plugin;
	}

	public static String disconnectMsg = "";

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerOuit(PlayerQuitEvent event) throws NullPointerException {
		Player player = event.getPlayer();
		if (player.hasPermission("combatlog.bypass")) {
			return;
		}
		if (!player.hasPermission("combatlog.bypass") && plugin.taggedPlayers.containsKey(player.getName())) {
			if (plugin.removeTagOnLagout && !playerCombatLogged()) {
				plugin.taggedPlayers.remove(player.getName());
				return;
			}
			if (plugin.broadcastEnabled) {
				plugin.broadcastMsg(plugin.translateText(plugin.broadcastMessage.replace("<name>", player.getName())));
			}
			PlayerCombatLogEvent event1 = new PlayerCombatLogEvent(plugin, player, (plugin.tagDuration
					- (plugin.getCurrentTime() - (Long) plugin.taggedPlayers.get(player.getName()).longValue())));
			plugin.getServer().getPluginManager().callEvent(event1);
			if (plugin.killEnabled) {
				player.setHealth(0);
				plugin.killPlayers.add(player.getUniqueId().toString());
			}
			plugin.taggedPlayers.remove(player.getName());
		}
	}

	public static void setDisconnectMsg(String msg) {
		disconnectMsg = msg;
	}

	public boolean playerCombatLogged() {
		if (!disconnectMsg.equalsIgnoreCase("disconnect.overflow")
				&& !disconnectMsg.equalsIgnoreCase("disconnect.genericreason")
				&& !disconnectMsg.equalsIgnoreCase("disconnect.timeout")) {
			return true;
		}
		return false;

	}

}
