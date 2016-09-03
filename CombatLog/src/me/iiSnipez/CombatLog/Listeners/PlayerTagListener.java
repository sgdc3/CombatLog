package me.iiSnipez.CombatLog.Listeners;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.BoardColls;
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
						if (plugin.useNewFaction) {
							faction = BoardColl.get().getFactionAt(PS.valueOf(l));
							if (faction.getName().equalsIgnoreCase("SafeZone")) {
								return;
							}
						}
						if(plugin.useOldFaction){
							if(BoardColls.get().getFactionAt(PS.valueOf(l)).getName().equalsIgnoreCase("SafeZone")){
								return;
							}
						}
						if(plugin.useOldOldFaction){
							if(Board.getFactionAt(new FLocation(l)).getTag().equalsIgnoreCase("SafeZone")){
								return;
							}
						}
					}
					if (!plugin.taggedPlayers.containsKey(p.getName())) {
						plugin.taggedPlayers.put(p.getName(), plugin.getCurrentTime());
						if (plugin.taggedMessageEnabled) {
							if (damagee instanceof Player) {
								p.sendMessage(plugin.translateText(
										plugin.taggerMessage.replaceAll("<name>", ((Player) damagee).getName())));
							}
						}
						if (plugin.usesLibsDisguise && plugin.removeDisguiseEnabled)
							plugin.removeDisguise(p);
						if (plugin.removeFlyEnabled)
							plugin.removeFly(p);
						if (plugin.removeInvisPotion) {
							removePotion(p);
							removePotion((Player) damagee);
						}
					} else {
						plugin.taggedPlayers.remove(p.getName());
						plugin.taggedPlayers.put(p.getName(), plugin.getCurrentTime());
						if (plugin.removeDisguiseEnabled)
							plugin.removeDisguise(p);
						if (plugin.removeFlyEnabled)
							plugin.removeFly(p);
						if (plugin.removeInvisPotion) {
							removePotion(p);
							removePotion((Player) damagee);
						}
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
						if (plugin.useNewFaction) {
							faction = BoardColl.get().getFactionAt(PS.valueOf(l));
							if (faction.getName().equalsIgnoreCase("SafeZone")) {
								return;
							}
						}
						if(plugin.useOldFaction){
							if(BoardColls.get().getFactionAt(PS.valueOf(l)).getName().equalsIgnoreCase("SafeZone")){
								return;
							}
						}
						if(plugin.useOldOldFaction){
							if(Board.getFactionAt(new FLocation(l)).getTag().equalsIgnoreCase("SafeZone")){
								return;
							}
						}
					}
					if (!plugin.taggedPlayers.containsKey(p.getName())) {
						plugin.taggedPlayers.put(p.getName(), plugin.getCurrentTime());
						if (plugin.taggerMessageEnabled) {
							if (damager instanceof Player) {
								p.sendMessage(plugin.translateText(
										plugin.taggedMessage.replaceAll("<name>", ((Player) damager).getName())));
							}
						}
						if (plugin.usesLibsDisguise && plugin.removeDisguiseEnabled)
							plugin.removeDisguise(p);
						if (plugin.removeFlyEnabled)
							plugin.removeFly(p);
						if (plugin.removeInvisPotion) {
							removePotion(p);
							removePotion((Player) damager);
						}
					} else {
						plugin.taggedPlayers.remove(p.getName());
						plugin.taggedPlayers.put(p.getName(), plugin.getCurrentTime());
						if (plugin.removeDisguiseEnabled)
							plugin.removeDisguise(p);
						if (plugin.removeFlyEnabled)
							plugin.removeFly(p);
						if (plugin.removeInvisPotion) {
							removePotion(p);
							removePotion((Player) damager);
						}
					}
				}
			}
		}
	}

	private void removePotion(Player player) {
		for (PotionEffect potion : player.getActivePotionEffects()) {
			if (potion.getType().equals(PotionEffectType.INVISIBILITY)) {
				player.removePotionEffect(PotionEffectType.INVISIBILITY);
				if (plugin.removeInvisMessageEnabled)
					player.sendMessage(plugin.translateText(plugin.removeInvisMessage));
			}
		}
	}
}
