package me.iiSnipez.CombatLog;

public class Variables {

	CombatLog plugin;
	
	public Variables(CombatLog plugin){
		this.plugin = plugin;
	}
	
	public void getValues() {
		plugin.logInfo("[CombatLog] Loading combatlog.yml");
		// configuration
		plugin.updateCheckEnabled = plugin.clConfig.getCLConfig().getBoolean("UpdateCheck");
		plugin.autoDownloadEnabled = plugin.clConfig.getCLConfig().getBoolean("AutoDownload");
		plugin.MOTDEnabled = plugin.clConfig.getCLConfig().getBoolean("MOTD");
		plugin.broadcastEnabled = plugin.clConfig.getCLConfig().getBoolean("Broadcast");
		plugin.tagDuration = plugin.clConfig.getCLConfig().getInt("Tag-Duration");
		if(plugin.clConfig.getCLConfig().getStringList("Remove-Modes").contains("fly")){
			plugin.removeFlyEnabled = true;
		}
		if(plugin.clConfig.getCLConfig().getStringList("Remove-Modes").contains("disguise")){
			plugin.removeDisguiseEnabled = true;
		}
		plugin.removeTagOnKick = plugin.clConfig.getCLConfig().getBoolean("Remove-Tag-On-Kick");
		plugin.removeTagOnLagout = plugin.clConfig.getCLConfig().getBoolean("Remove-Tag-On-Lagout");
		plugin.blockCommandsEnabled = plugin.clConfig.getCLConfig().getBoolean("Block-Commands");
		plugin.blockCommandNames = plugin.clConfig.getCLConfig().getStringList("Commands");
		plugin.blockTeleportationEnabled = plugin.clConfig.getCLConfig().getBoolean("Block-Teleportation");
		plugin.blockEnderpearlsEnabled = plugin.clConfig.getCLConfig().getBoolean("Block-Enderpearls");
		plugin.disableWorldNames = plugin.clConfig.getCLConfig().getStringList("Disabled-Worlds");
		plugin.killEnabled = plugin.clConfig.getCLConfig().getBoolean("Kill");
		// messages
		plugin.updateCheckMessage = plugin.clConfig.getCLConfig().getString("UpdateCheckMessage");
		if (!plugin.updateCheckMessage.equalsIgnoreCase("false")) {
			plugin.updateCheckMessageEnabled = true;
		}
		plugin.MOTDMessage = plugin.clConfig.getCLConfig().getString("MOTDMessage");
		if (!plugin.MOTDMessage.equalsIgnoreCase("false")) {
			plugin.MOTDMessageEnabled = true;
		}
		plugin.broadcastMessage = plugin.clConfig.getCLConfig().getString("BroadcastMessage");
		if (!plugin.broadcastMessage.equalsIgnoreCase("false")) {
			plugin.broadcastMessageEnabled = true;
		}
		plugin.taggerMessage = plugin.clConfig.getCLConfig().getString("TaggerMessage");
		if (!plugin.taggerMessage.equalsIgnoreCase("false")) {
			plugin.taggerMessageEnabled = true;
		}
		plugin.taggedMessage = plugin.clConfig.getCLConfig().getString("TaggedMessage");
		if (!plugin.taggedMessage.equalsIgnoreCase("false")) {
			plugin.taggedMessageEnabled = true;
		}
		plugin.untagMessage = plugin.clConfig.getCLConfig().getString("UntagMessage");
		if (!plugin.untagMessage.equalsIgnoreCase("false")) {
			plugin.untagMessageEnabled = true;
		}
		plugin.tagTimeMessage = plugin.clConfig.getCLConfig().getString("InCombatMessage");
		if (!plugin.tagTimeMessage.equalsIgnoreCase("false")) {
			plugin.tagTimeMessageEnabled = true;
		}
		plugin.notInCombatMessage = plugin.clConfig.getCLConfig().getString("NotInCombatMessage");
		if (!plugin.notInCombatMessage.equalsIgnoreCase("false")) {
			plugin.notInCombatMessageEnabled = true;
		}
		plugin.removeModesMessage = plugin.clConfig.getCLConfig().getString("RemoveModesMessage");
		if (!plugin.removeModesMessage.equalsIgnoreCase("false")) {
			plugin.removeModesMessageEnabled = true;
		}
		plugin.blockCommandsMessage = plugin.clConfig.getCLConfig().getString("BlockCommandsMessage");
		if (!plugin.blockCommandsMessage.equalsIgnoreCase("false")) {
			plugin.blockCommandsMessageEnabled = true;
		}
		plugin.blockTeleportationMessage = plugin.clConfig.getCLConfig().getString("BlockTeleportationMessage");
		if (!plugin.blockTeleportationMessage.equalsIgnoreCase("false")) {
			plugin.blockTeleportationMessageEnabled = true;
		}
		plugin.killMessage = plugin.clConfig.getCLConfig().getString("KillMessage");
		if (!plugin.killMessage.equalsIgnoreCase("false")) {
			plugin.killMessageEnabled = true;
		}
	}
}
