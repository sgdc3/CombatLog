/*
 *    @author iiSnipez
 *    This is a rewrite of the popular CombatLog plugin (http://dev.bukkit.org/bukkit-plugins/combatlog/) made by JackProehl
 */

package me.iiSnipez.CombatLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.iiSnipez.CombatLog.Events.PlayerUntagEvent;
import me.iiSnipez.CombatLog.Listeners.EntityDamageByEntityListener;
import me.iiSnipez.CombatLog.Listeners.PlayerCommandPreprocessListener;
import me.iiSnipez.CombatLog.Listeners.PlayerDeathListener;
import me.iiSnipez.CombatLog.Listeners.PlayerDisguiseListener;
import me.iiSnipez.CombatLog.Listeners.PlayerJoinListener;
import me.iiSnipez.CombatLog.Listeners.PlayerKickListener;
import me.iiSnipez.CombatLog.Listeners.PlayerMoveListener;
import me.iiSnipez.CombatLog.Listeners.PlayerQuitListener;
import me.iiSnipez.CombatLog.Listeners.PlayerTagListener;
import me.iiSnipez.CombatLog.Listeners.PlayerTeleportListener;
import me.iiSnipez.CombatLog.Listeners.PlayerToggleFlightListener;
import me.libraryaddict.disguise.DisguiseAPI;

public class CombatLog extends JavaPlugin {

	public Logger log = Logger.getLogger("Minecraft");
	public PluginFile clConfig;
	public CommandExec commandExec;
	public Variables vars;
	public boolean usesLibsDisguise = false;
	public boolean usesFactions = false;
	public Updater updater;
	public boolean updateCheckEnabled = false;
	public boolean autoDownloadEnabled = false;
	public boolean updateAvaliable = false;
	public boolean MOTDEnabled = false;
	public boolean broadcastEnabled = false;
	public int tagDuration = 10;
	public boolean removeFlyEnabled = false;
	public boolean removeDisguiseEnabled = false;
	public boolean removeTagOnKick = false;
	public boolean removeTagOnLagout = false;
	public boolean removeInvisPotion = false;
	public boolean blockCommandsEnabled = false;
	public List<String> blockCommandNames = new ArrayList<String>();
	public boolean blockTeleportationEnabled = false;
	public boolean blockEnderpearlsEnabled = false;
	public List<String> disableWorldNames = new ArrayList<String>();
	public boolean killEnabled = false;
	public String updateCheckMessage = "";
	public boolean updateCheckMessageEnabled = false;
	public String MOTDMessage = "";
	public boolean MOTDMessageEnabled = false;
	public String broadcastMessage = "";
	public boolean broadcastMessageEnabled = false;
	public String taggerMessage = "";
	public boolean taggerMessageEnabled = false;
	public String taggedMessage = "";
	public boolean taggedMessageEnabled = false;
	public String untagMessage = "";
	public boolean untagMessageEnabled = false;
	public String tagTimeMessage = "";
	public boolean tagTimeMessageEnabled = false;
	public String notInCombatMessage = "";
	public boolean notInCombatMessageEnabled = false;
	public String removeModesMessage = "";
	public boolean removeModesMessageEnabled = false;
	public String removeInvisMessage = "";
	public boolean removeInvisMessageEnabled = false;
	public String blockCommandsMessage = "";
	public boolean blockCommandsMessageEnabled = false;
	public String blockTeleportationMessage = "";
	public boolean blockTeleportationMessageEnabled = false;
	public String killMessage = "";
	public boolean killMessageEnabled = false;
	public HashMap<String, Long> taggedPlayers = new HashMap<String, Long>();
	public ArrayList<String> killPlayers = new ArrayList<String>();
	public boolean useNewFaction = false;
	public boolean useOldFaction = false;
	public boolean useOldOldFaction = false;

	public void onEnable() {
		initiateVars();
		loadSettings();
		updateCheck();
		initiateListeners();
		initiateCmds();
		LogHandler();
		enableTimer();
		if (clConfig.getCLConfig().getBoolean("Metrics")) {
			startMetrics();
		}
		checkForPlugins();
		logInfo("[CombatLog] Enabled.");
	}

	public void onDisable() {
		taggedPlayers.clear();
		logInfo("[CombatLog] Disabled.");
	}

	public void updateCheck() {
		if (updateCheckEnabled) {
			if (autoDownloadEnabled) {
				updater = new Updater(this, 45749, getFile(), Updater.UpdateType.DEFAULT, false);
				if (updater.getResult() == Updater.UpdateResult.SUCCESS) {
					logInfo("[CombatLog] Successfully downloaded new update.");
				}
			} else {
				updater = new Updater(this, 45749, getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
				if (updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE) {
					logInfo("[CombatLog] New update at " + updater.getLatestFileLink());
					logInfo("[CombatLog] Use the command 'cl update' to download the newest version.");
					updateAvaliable = true;
				}
			}
		}
	}

	public void update(Player player) {
		updater = new Updater(this, 45749, getFile(), Updater.UpdateType.NO_VERSION_CHECK, false);
		if (updater.getResult() == Updater.UpdateResult.SUCCESS) {
			player.sendMessage(translateText("&8[&4CombatLog&8] &aUpdate downloaded successfully!"));
		} else if (updater.getResult() == Updater.UpdateResult.FAIL_DOWNLOAD) {
			player.sendMessage(translateText("&8[&4CombatLog&8] &cFailed to download the newest version!"));
		}
	}

	public void consoleUpdate(ConsoleCommandSender console) {
		if (updateAvaliable) {
			updater = new Updater(this, 45749, getFile(), Updater.UpdateType.NO_VERSION_CHECK, false);
			if (updater.getResult() == Updater.UpdateResult.SUCCESS) {
				console.sendMessage(ChatColor.GREEN + "[CombatLog] Update downloaded successfully!");
			} else if (updater.getResult() == Updater.UpdateResult.FAIL_DOWNLOAD) {
				console.sendMessage(ChatColor.RED + "[CombatLog] Failed to download the newest version!");
			}
		}
	}

	public void checkForPlugins() {
		if (getServer().getPluginManager().getPlugin("LibsDisguises") == null) {
			usesLibsDisguise = false;
		} else {
			logInfo("&c[CombatLog] LibsDisguises plugin found! Disguise removal will work.");
			usesLibsDisguise = true;
		}
		if (getServer().getPluginManager().getPlugin("Factions") == null) {
			usesFactions = false;
		} else {
			logInfo("[CombatLog] Factions plugin found! Safezone untagging will work.");
			usesFactions = true;
			String version = getServer().getPluginManager().getPlugin("Factions").getDescription().getVersion();
			if (version.substring(0, 3).equalsIgnoreCase("2.7") || version.substring(0, 3).equalsIgnoreCase("2.8")
					|| version.substring(0, 3).equalsIgnoreCase("2.9")) {
				useNewFaction = true;
			} else {
				useOldFaction = true;
			}
			if (version.substring(0, 1).equalsIgnoreCase("1")) {
				useOldOldFaction = true;
			}
		}
	}

	public void startMetrics() {
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void enableTimer() {
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				Iterator<Map.Entry<String, Long>> iter = taggedPlayers.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, Long> c = iter.next();
					Player player = getServer().getPlayer(c.getKey());
					if (getCurrentTime() - (long) c.getValue().longValue() >= tagDuration) {
						iter.remove();
						PlayerUntagEvent event = new PlayerUntagEvent(player);
						getServer().getPluginManager().callEvent(event);
						if (untagMessageEnabled) {
							player.sendMessage(translateText(untagMessage));
						}
					}
				}
			}
		}, 0L, 20L);
	}

	public void LogHandler() {
		log.addHandler(new Handler() {
			public void publish(LogRecord logRecord) {
				String s = logRecord.getMessage();
				if (s.contains(" lost connection: ")) {
					String[] a = s.split(" ");
					String DisconnectMsg = a[3];
					PlayerQuitListener.setDisconnectMsg(DisconnectMsg);
				}
			}

			public void flush() {
			}

			public void close() throws SecurityException {
			}
		});
	}

	public void initiateCmds() {
		getCommand("combatlog").setExecutor(commandExec);
		getCommand("tag").setExecutor(commandExec);
	}

	public void initiateListeners() {
		getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerKickListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerTagListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerTeleportListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerToggleFlightListener(this), this);
		if (usesLibsDisguise) {
			getServer().getPluginManager().registerEvents(new PlayerDisguiseListener(this), this);
		}
	}

	public void loadSettings() {
		clConfig.getCLConfig().options().copyDefaults(true);
		clConfig.saveDefault();
		clConfig.reloadCLConfig();

		vars.getValues();
	}

	public void initiateVars() {
		clConfig = new PluginFile(this);
		commandExec = new CommandExec(this);
		vars = new Variables(this);
	}

	public void removeDisguise(Player player) {
		if (usesLibsDisguise && removeDisguiseEnabled && DisguiseAPI.isDisguised(player)) {
			DisguiseAPI.undisguiseToAll(player);
			if (removeModesMessageEnabled) {
				player.sendMessage(translateText(removeModesMessage.replaceAll("<mode>", "disguise")));
			}
		}
	}

	public void removeFly(Player player) {
		if (player.isFlying() && removeFlyEnabled) {
			player.setFlying(false);
			player.setAllowFlight(false);
			if (removeModesMessageEnabled) {
				player.sendMessage(translateText(removeModesMessage.replaceAll("<mode>", "fly")));
			}
		}
	}

	public long getCurrentTime() {
		return System.currentTimeMillis() / 1000L;
	}

	public void broadcastMsg(String string) {
		getServer().broadcastMessage(translateText(string));
	}

	public void logInfo(String string) {
		log.info(translateText(string));
	}

	public String translateText(String string) {
		return ChatColor.translateAlternateColorCodes('&', "" + string);
	}

}