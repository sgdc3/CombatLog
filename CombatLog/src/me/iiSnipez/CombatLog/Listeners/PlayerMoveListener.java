package me.iiSnipez.CombatLog.Listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.BoardColls;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.massivecore.ps.PS;

import me.iiSnipez.CombatLog.CombatLog;

public class PlayerMoveListener implements Listener {

	CombatLog plugin;
	Faction factionIn;

	public PlayerMoveListener(CombatLog plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (plugin.taggedPlayers.containsKey(player.getName())) {
			if (plugin.usesFactions) {
				if (plugin.useNewFaction) {
					Location l = player.getLocation();
					factionIn = BoardColl.get().getFactionAt(PS.valueOf(l));
					if (factionIn.equals(FactionColl.get().getSafezone())) {
						plugin.taggedPlayers.remove(player.getName());
						if (plugin.untagMessageEnabled) {
							player.sendMessage(plugin.translateText(plugin.untagMessage));
						}
						return;
					}
				}
				if(plugin.useOldFaction){
					Location l = player.getLocation();
					factionIn = BoardColls.get().getFactionAt(PS.valueOf(l));
					if(factionIn.getName().equalsIgnoreCase("SafeZone")){
						plugin.taggedPlayers.remove(player.getName());
						if (plugin.untagMessageEnabled) {
							player.sendMessage(plugin.translateText(plugin.untagMessage));
						}
						return;
					}
				}
				if(plugin.useOldOldFaction){
					Location l = player.getLocation();
					com.massivecraft.factions.Faction factionInOld = Board.getFactionAt(new FLocation(l));
					if(factionInOld.getTag().equalsIgnoreCase("SafeZone")){
						plugin.taggedPlayers.remove(player.getName());
						if (plugin.untagMessageEnabled) {
							player.sendMessage(plugin.translateText(plugin.untagMessage));
						}
						return;
					}
				}
			}
			if (plugin.removeFlyEnabled) {
				plugin.removeFly(player);
			}
			if (plugin.removeDisguiseEnabled) {
				plugin.removeDisguise(player);
			}
		}
	}

}
