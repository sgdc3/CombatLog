package me.iiSnipez.CombatLog.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.iiSnipez.CombatLog.CombatLog;

public class PlayerCombatLogEvent extends Event implements Cancellable {

	private boolean cancelled;
	private Player player;
	public long tagTimeRemaining;
	CombatLog plugin;

	private static final HandlerList handlerList = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlerList;
	}

	public HandlerList getHandlers() {
		return handlerList;
	}
	
	public PlayerCombatLogEvent(CombatLog plugin, Player player, long tagTimeRemaining) {
		this.plugin = plugin;
		this.player = player;
		this.tagTimeRemaining = tagTimeRemaining;
	}

	public Player getPlayer() {
		return player;
	}

	public long getTagTimeRemainging() {
		return (plugin.tagDuration
				- (plugin.getCurrentTime() - (Long) plugin.taggedPlayers.get(player.getName()).longValue()));
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean b) {
		this.cancelled = b;
	}

}
