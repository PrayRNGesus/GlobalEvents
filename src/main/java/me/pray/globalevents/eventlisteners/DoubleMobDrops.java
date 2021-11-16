package me.pray.globalevents.eventlisteners;

import me.pray.globalevents.customevents.Events;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DoubleMobDrops implements Listener {

    Events events;

    public DoubleMobDrops(Events events) {
        this.events = events;
    }

    @EventHandler
    public void doubleMobDropEvent(EntityDeathEvent event) {
        if (!events.isDoubleMobDrops()) return;
        if (event.getEntity() instanceof Player) return;
        if (!(event.getEntity().getKiller() instanceof Player)) return;
        List<ItemStack> drops = event.getDrops();
        Player player = event.getEntity().getKiller();
        Location loc = event.getEntity().getLocation();
        drops.forEach(drop -> {
            player.getWorld().dropItemNaturally(loc, drop);
        });
    }

}
