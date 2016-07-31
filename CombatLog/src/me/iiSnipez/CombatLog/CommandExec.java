package me.iiSnipez.CombatLog;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandExec implements CommandExecutor {

	public CombatLog plugin;

	public CommandExec(CombatLog plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getLabel().equalsIgnoreCase("combatlog") || cmd.getLabel().equalsIgnoreCase("cl")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length == 0) {
					player.sendMessage(plugin.translateText("&8[&4CombatLog&8]&7 Originally developed by JackProehl"));
					player.sendMessage(plugin.translateText("&8[&4CombatLog&8]&7 Update developed by iiSnipez"));
					player.sendMessage(plugin.translateText("&cUse &7/cl help &cto view the commands."));
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help")) {
						player.sendMessage(
								plugin.translateText("&cUse &7/tag &cor &7/ct &cto view if you are in combat."));
						if (player.hasPermission("combatlog.reload") || player.isOp()) {
							player.sendMessage(
									plugin.translateText("&cUse &7/cl reload &cto reload the configuration."));
						}
					} else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
						if (player.hasPermission("combatlog.reload") || player.isOp()) {
							plugin.loadSettings();
							player.sendMessage(plugin.translateText("&8[&4CombatLog&8] &aConfiguration reloaded."));
						} else {
							player.sendMessage(
									plugin.translateText("&4You do not have permission to use this command."));
						}
					} else if (args[0].equalsIgnoreCase("update") && player.hasPermission("combatlog.update")) {
						if(plugin.updateAvaliable)
							plugin.update(player);
						else
							player.sendMessage(plugin.translateText("&8[&4CombatLog&8] &cNo update was detected."));
					}
				}
			} else if (sender instanceof ConsoleCommandSender) {
				ConsoleCommandSender console = (ConsoleCommandSender) sender;
				if (args.length == 0) {
					console.sendMessage("[CombatLog] Use '/cl help' to view all of the commands.");
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help")) {
						console.sendMessage("[CombatLog] /cl reload - reloads the configuration.");
					} else if (args[0].equalsIgnoreCase("reload")) {
						plugin.loadSettings();
						console.sendMessage("[CombatLog] Configuration reloaded.");
					} else if (args[0].equalsIgnoreCase("update")) {
						if(plugin.updateAvaliable)
							plugin.consoleUpdate(console);
						else
							console.sendMessage(ChatColor.RED + "[CombatLog] No update was detected.");
					}
				}
			}
		} else if (cmd.getLabel().equalsIgnoreCase("tag") || cmd.getLabel().equalsIgnoreCase("ct")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				String id = player.getName();
				if (plugin.taggedPlayers.containsKey(id) && plugin.tagDuration
						- (plugin.getCurrentTime() - (Long) plugin.taggedPlayers.get(id).longValue()) >= 1L) {
					player.sendMessage(
							plugin.translateText(plugin.tagTimeMessage.replaceAll("<time>", "" + (plugin.tagDuration
									- (plugin.getCurrentTime() - (Long) plugin.taggedPlayers.get(id).longValue())))));
				}
				if (plugin.taggedPlayers.containsKey(id) && plugin.tagDuration
						- (plugin.getCurrentTime() - (Long) plugin.taggedPlayers.get(id).longValue()) < 1L) {
					player.sendMessage(
							plugin.translateText(plugin.tagTimeMessage.replaceAll("<time>", "" + (plugin.tagDuration
									- (plugin.getCurrentTime() - (Long) plugin.taggedPlayers.get(id).longValue())))));
				}
				if (!plugin.taggedPlayers.containsKey(id)) {
					player.sendMessage(plugin.translateText(plugin.notInCombatMessage));
				}
			}else if(sender instanceof ConsoleCommandSender){
				ConsoleCommandSender console = (ConsoleCommandSender) sender;
				console.sendMessage("[CombatLog] The console cannot use this command.");
			}
		}
		return false;
	}

}
