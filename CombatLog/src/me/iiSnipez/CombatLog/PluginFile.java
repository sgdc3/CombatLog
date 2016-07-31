package me.iiSnipez.CombatLog;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class PluginFile extends YamlConfiguration {

	public CombatLog plugin;

	public PluginFile(CombatLog plugin)
	  {
	    this.plugin = plugin;
	  }

	public FileConfiguration clConfig = null;
	public File clConfigFile = null;

	public void reloadCLConfig() {
		if (clConfigFile == null) {
			clConfigFile = new File(plugin.getDataFolder(), "combatlog.yml");
		}
		clConfig = YamlConfiguration.loadConfiguration(clConfigFile);
	}

	public FileConfiguration getCLConfig() {
		if (clConfig == null) {
			reloadCLConfig();
		}
		return clConfig;
	}

	public void saveCLConfig() {
		if ((clConfig == null) || (clConfigFile == null)) {
			return;
		}
		try {
			getCLConfig().save(clConfigFile);
		} catch (IOException ex) {
			plugin.log.warning("Could not save config to " + clConfigFile);
			plugin.log.warning(ex.getMessage());
		}
	}

	public void saveDefault() {
		if (!clConfigFile.exists()) {
			plugin.saveResource("combatlog.yml", false);
		}
	}

}
