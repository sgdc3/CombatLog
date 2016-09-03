package me.iiSnipez.CombatLog.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.iiSnipez.CombatLog.CombatLog;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.events.DisguiseEvent;

public class PlayerDisguiseListener implements Listener{
	
	CombatLog plugin;
	
	public PlayerDisguiseListener(CombatLog plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerDisguise(DisguiseEvent event){
		if(event.getEntity() instanceof Player){
			Player player = (Player) event.getEntity();
			if(plugin.usesLibsDisguise && plugin.removeDisguiseEnabled && plugin.taggedPlayers.containsKey(player.getName())){
				DisguiseAPI.undisguiseToAll(player);
				event.setCancelled(true);
			}
		}
	}

}
