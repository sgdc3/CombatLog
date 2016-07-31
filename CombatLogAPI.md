# CombatLog API
CombatLog now has an API that allows developers to hook into the CombatLog plugin and listen for three events:
- PlayerCombatLogEvent
- PlayerTagEvent
- PlayerUntagEvent

Using these three events is quite simple, one can use them just like normal events.
```java
public class YourListenerClass implements Listener {

    @EventHandler
    public void onCombatLogEvent(PlayerCombatLogEvent event) {
        event.getPlayer(); //Returns the player who combat logged
        event.getTimeRemaining(); //Returns the time remaining in the tag
    }
    
    @EventHandler
    public void onPlayerTagEvent(PlayerTagEvent event) {
        event.getDamager(); //Returns the player who DOES the damage
        event.getDamagee(); //Returns the player who RECEIVES the damage
    }
    
    @EventHandler
    public void onPlayerUntagEvent(PlayerUntagEvent event) {
        event.getPlayer(); //Returns the player who is untagged
    }
}
```
