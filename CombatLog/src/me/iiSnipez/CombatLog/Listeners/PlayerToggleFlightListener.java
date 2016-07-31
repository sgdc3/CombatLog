package me.iiSnipez.CombatLog.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import me.iiSnipez.CombatLog.CombatLog;

public class PlayerToggleFlightListener implements Listener {

	CombatLog plugin;

	public PlayerToggleFlightListener(CombatLog plugin) {
		this.plugin = plugin;
	}
	
	public void onFlightToggle(PlayerToggleFlightEvent event){
		Player player = event.getPlayer();
		if(plugin.removeFlyEnabled && !player.hasPermission("combatlog.bypass") && plugin.taggedPlayers.containsKey(player.getName())){
			player.setFlying(false);
			player.setAllowFlight(false);
			event.setCancelled(true);
			if(plugin.removeModesMessageEnabled){
				player.sendMessage(plugin.translateText(plugin.removeModesMessage));
			}
		}
	}

}
