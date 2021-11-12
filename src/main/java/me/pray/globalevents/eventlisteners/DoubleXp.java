package me.pray.globalevents.eventlisteners;

import me.pray.globalevents.GlobalEvents;
import me.pray.globalevents.customevents.Events;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class DoubleXp extends Events {

    public DoubleXp(GlobalEvents plugin) {
        super(plugin);
    }

    @EventHandler
    public void doubleXpEvent(PlayerExpChangeEvent event) {
        if(!plugin.getDoubleXp()) return;
        int newXp = (event.getAmount() * 2);
        event.setAmount(newXp);
    }

}
