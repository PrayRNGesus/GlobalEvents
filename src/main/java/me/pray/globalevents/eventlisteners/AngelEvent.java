package me.pray.globalevents.eventlisteners;

import me.pray.globalevents.GlobalEvents;
import me.pray.globalevents.customevents.Events;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AngelEvent implements Listener {

    Events events;

    public AngelEvent(Events events) {
        this.events = events;
    }

    @EventHandler
    public void onSpawn(PlayerRespawnEvent event) {
        if (!events.getAngelEvent()) return;
        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskLater(events.plugin, () -> {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
        }, 5L);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!events.getAngelEvent()) return;
        Player player = event.getPlayer();
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {
        if (!events.getAngelEvent()) return;
        Player player = event.getPlayer();

        if (player.hasPotionEffect(PotionEffectType.SPEED)) {
            player.removePotionEffect(PotionEffectType.SPEED);
        }

        if (player.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
            player.removePotionEffect(PotionEffectType.FAST_DIGGING);
        }

    }

}
