package me.pray.globalevents.eventlisteners;

import me.pray.globalevents.customevents.Events;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class DoubleXp implements Listener {

    Events events;

    public DoubleXp(Events events) {
        this.events = events;
    }

    @EventHandler
    public void doubleXpEvent(PlayerExpChangeEvent event) {
        if(!events.isDoubleXp()) return;
        int newXp = (event.getAmount() * 2);
        event.setAmount(newXp);
    }

}
