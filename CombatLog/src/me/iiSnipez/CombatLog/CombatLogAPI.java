package me.iiSnipez.CombatLog;

import org.bukkit.entity.Player;

public class CombatLogAPI {

	CombatLog plugin;
	
	private CombatLogAPI (CombatLog plugin){
		this.plugin = plugin;
	}
	
	public boolean isInCombat(Player player){
		if(plugin.taggedPlayers.containsKey(player.getName()))
			return true;
		else
			return false;
	}
	
	public long getTimeRemaining(Player player){
		if(plugin.taggedPlayers.containsKey(player.getName()))
			return (plugin.tagDuration - (plugin.getCurrentTime() - (Long) plugin.taggedPlayers.get(player.getName()).longValue()));
		else
			return 0L;
	}
	
}
