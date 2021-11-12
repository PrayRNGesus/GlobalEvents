package me.pray.globalevents.eventlisteners;

import me.pray.globalevents.GlobalEvents;
import me.pray.globalevents.customevents.Events;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DoubleMobDamage extends Events {

    public DoubleMobDamage(GlobalEvents plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if(!plugin.getDoubleMobDamage()) return;
        if(event.getDamager() instanceof Player) return;
        if(!(event.getEntity() instanceof Player)) return;
        event.setDamage(event.getDamage() * 2);
    }

}
