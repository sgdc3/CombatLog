# CombatLog Configuration
With the newest update, the old config.yml and messages.properties were removed and merged into one file: combatlog.yml

Default values of the new config file.
```YAML
############################################################
# +-------- Designed and Developed by JackProehl --------+ #
# |---------- Updated and Managed by iiSnipez -----------| #
# |---- Consider donating to Jack: JohnProehl@me.com ----| #
# +-- Consider donating to iiSnipez: paypal.me/Jarod05 --+ #
############################################################

# Please open a ticket to report all bugs and glitches on BukkitDev.

# Do not backspace the version number!
Version: 2.0.0

# Set UpdateCheck to true to check for available updates.
UpdateCheck-Enabled: true

# Set AutoDownload to true automatically download the latest version.
AutoDownload: false

# Set DownloadCommand to true to allow people with the necessary permission
# to issue a command to update CombatLog when an update is available.
DownloadCommand: true

# To opt-out set Metrics to false.
Metrics: true

# Set Broadcast to true to broadcast when a player CombatLogs.
Broadcast-Enabled: true

# Set Tag-Duration to how long a player is in Combat in seconds.
# Set PvP-Enabled to true to enable tagging from Player vs Player combat.
Tag-Duration: 10
PvP-Enabled: true

# Add modes you want removed during Combat under Remove-Modes.
# Available Modes: fly, disguise.
Remove-Modes:
- fly
- disguise

# Set onKick to true to remove CombatTags when players are kicked.
# Set onLagout to true to remove CombatTags when players lag out.
Remove-Tag-On-Kick: true
Remove-Tag-On-Lagout: false

# These commands are BLOCKED during combat, add ones you would like to block in combat (add aliases too!)
Block-All-Commands: false
Block-Commands: true
Command-Blacklist:
  - home
  - spawn
  - tpa
  - tpaccept
  - tpahere
  - warp
  - fly
  - disguise

# Set Block-Teleportation-Enable to true to block all types of teleportation.
# Set Block-Enderpearls-Enabled to true to block only enderpearling. (If above is true this doesn't need to change)
Block-Teleportation-Enabled: true
Block-Enderpearls-Enabled: false

# Set Disabled-Worlds-Enabled to true to disable CombatTag in worlds listed under Worlds.
# List the worlds you want disabled under Worlds.
Disabled-Worlds-Enabled: true
Worlds:
- world_custom

# Set Enabled to true to kill players when they CombatLog.
Kill-Enabled: true

############################################################
# +------------------------------------------------------+ #
# |                       Messages                       | #
# +------------------------------------------------------+ #
############################################################

# TO DISABLE A MESSAGE, SET IT AS 'false'!

# Message sent to ops to notify them of a CombatLog update.
# Remove the part with the command and change DownloadCommand: false in the config if you do not wish to use the command.
UpdateCheckMessage: '&8[&4CombatLog&8]&c CombatLog update available! Use &f/cl update &cto download the update.'

# Broadcast that it sent when a player CombatLogs.
BroadcastMessage: '&8[&4CombatLog&8]&c <name> &7logged out while in combat!'

# Message sent to a player when he attacks someone or something.
TaggerMessage: '&8[&4CombatLog&8]&7 You have combat tagged <name>. Do not logout!'

# Message sent to a player when he has been attacked by someone or something.
TaggedMessage: '&8[&4CombatLog&8]&7 You have been combat tagged by <name>. Do not logout!'

# Message sent to the player when his tag duration is over.
UntagMessage: '&8[&4CombatLog&8]&7 You are no longer in combat. You may logout.'

# Message sent to a player when he types /ct to check his remaining Tag time.
InCombatMessage: '&8[&4CombatLog&8]&7 You are tagged for &c<time> &7more seconds.'

# Message sent to a player who types /ct if he is not in Combat.
NotInCombatMessage: '&8[&4CombatLog&8]&7 You are not in Combat.'

# Message sent to the player when his fly and/or disguise mode is removed.
RemoveModesMessage: '&8[&4CombatLog&8]&c Your <mode> has been removed.'

# Message sent when the player attempts to use a blocked command.
BlockCommandsMessage: '&8[&4CombatLog&8]&c That command is not allowed during Combat.'

# Message sent when the player attempts to teleport.
BlockTeleportationMessage: '&8[&4CombatLog&8]&c You cannot teleport while in Combat.'

# Message sent to the CombatLogger upon logging back in after being killed for CombatLogging.
KillMessage: '&8[&4CombatLog&8]&c You were killed for CombatLogging!'
```