package me.iiSnipez.CombatLog.Listeners;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.ps.PS;

import me.iiSnipez.CombatLog.CombatLog;
import me.iiSnipez.CombatLog.Events.PlayerTagEvent;

public class PlayerTagListener implements Listener {

	CombatLog plugin;
	Faction faction;

	public PlayerTagListener(CombatLog plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onTagEvent(PlayerTagEvent event) {
		Player damager = event.getDamager();
		Player damagee = event.getDamagee();
		tagDamager(damager, damagee);
		tagDamagee(damager, damagee);
	}

	private void tagDamager(Entity damager, Entity damagee) {
		if (damager instanceof Player) {
			Player p = (Player) damager;
			if (!p.hasPermission("combatlog.bypass")) {
				Location l = p.getLocation();
				if (!plugin.disableWorldNames.contains(p.getWorld().getName())) {
					if (plugin.usesFactions) {
						faction = BoardColl.get().getFactionAt(PS.valueOf(l));
						if (faction.getName().equalsIgnoreCase("SafeZone")) {
							return;
						}
					}
					if (!plugin.taggedPlayers.containsKey(p.getName())) {
						plugin.taggedPlayers.put(p.getName(), plugin.getCurrentTime());
						if (plugin.taggedMessageEnabled) {
							if (damagee instanceof Player) {
								p.sendMessage(plugin
										.translateText(plugin.taggerMessage.replaceAll("<name>", damagee.getName())));
							}
						}
						if (plugin.usesLibsDisguise && plugin.removeDisguiseEnabled)
							plugin.removeDisguise(p);
						if (plugin.removeFlyEnabled)
							plugin.removeFly(p);
					} else {
						plugin.taggedPlayers.remove(p.getName());
						plugin.taggedPlayers.put(p.getName(), plugin.getCurrentTime());
						if (plugin.removeDisguiseEnabled)
							plugin.removeDisguise(p);
						if (plugin.removeFlyEnabled)
							plugin.removeFly(p);
					}
				}
			}
		}
	}

	private void tagDamagee(Entity damager, Entity damagee) {
		if (damagee instanceof Player) {
			Player p = (Player) damagee;
			if (!p.hasPermission("combatlog.bypass")) {
				Location l = p.getLocation();
				if (!plugin.disableWorldNames.contains(p.getWorld().getName())) {
					if (plugin.usesFactions) {
						faction = BoardColl.get().getFactionAt(PS.valueOf(l));
						if (faction.getName().equalsIgnoreCase("SafeZone")) {
							return;
						}
					}
					if (!plugin.taggedPlayers.containsKey(p.getName())) {
						plugin.taggedPlayers.put(p.getName(), plugin.getCurrentTime());
						if (plugin.taggerMessageEnabled) {
							if (damager instanceof Player) {
								p.sendMessage(plugin
										.translateText(plugin.taggedMessage.replaceAll("<name>", damager.getName())));
							}
						}
						if (plugin.usesLibsDisguise && plugin.removeDisguiseEnabled)
							plugin.removeDisguise(p);
						if (plugin.removeFlyEnabled)
							plugin.removeFly(p);
					} else {
						plugin.taggedPlayers.remove(p.getName());
						plugin.taggedPlayers.put(p.getName(), plugin.getCurrentTime());
						if (plugin.removeDisguiseEnabled)
							plugin.removeDisguise(p);
						if (plugin.removeFlyEnabled)
							plugin.removeFly(p);
					}
				}
			}
		}
	}
}
