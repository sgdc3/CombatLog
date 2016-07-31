package me.iiSnipez.CombatLog.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.iiSnipez.CombatLog.CombatLog;

public class PlayerDeathListener implements Listener {

	public CombatLog plugin;

	public PlayerDeathListener(CombatLog plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event){
		Player player = event.getEntity().getPlayer();
		if(plugin.taggedPlayers.containsKey(player.getName())){
			plugin.taggedPlayers.remove(player.getName());
		}
	}

}
