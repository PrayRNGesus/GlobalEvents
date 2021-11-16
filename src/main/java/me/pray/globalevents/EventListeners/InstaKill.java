package me.pray.globalevents.EventListeners;

import me.pray.globalevents.CustomEvents.Events;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class InstaKill implements Listener {

    Events events;

    public InstaKill(Events events) {
        this.events = events;
    }

    @EventHandler
    public void onInstaKillEvent(EntityDamageByEntityEvent event) {
        if (!events.isInstaKill()) return;
        if (event.getEntity() instanceof Player) return;
        if (event.getEntity() instanceof Damageable) {
            Damageable entity = (Damageable) event.getEntity();
            entity.setHealth(0);
        }


    }

}
