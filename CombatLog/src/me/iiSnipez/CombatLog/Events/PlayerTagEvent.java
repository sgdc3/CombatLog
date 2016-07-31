package me.iiSnipez.CombatLog.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerTagEvent extends Event implements Cancellable {

	private Player damager;
	private Player damagee;
	private int time;
	private boolean cancelled;

	public PlayerTagEvent(Player damager, Player damagee, int time) {
		this.damager = damager;
		this.damagee = damagee;
		this.time = time;
	}

	public Player getDamager() {
		return damager;
	}
	
	public Player getDamagee() {
		return damagee;
	}
	
	public int getTagTime(){
		return time;
	}

	private static HandlerList handlerList = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlerList;
	}

	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean b) {
		this.cancelled = b;
	}

}
