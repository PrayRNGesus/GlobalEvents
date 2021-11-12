package me.pray.globalevents.eventlisteners;

import me.pray.globalevents.GlobalEvents;
import me.pray.globalevents.customevents.Events;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class AcidicWater extends Events {

    public AcidicWater(GlobalEvents plugin) {
        super(plugin);
    }

    @EventHandler
    public void isInWater(PlayerMoveEvent event) {
        if(!plugin.getAcidicWater()) return;
        if(!event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) return;

        Material mat = event.getPlayer().getLocation().getBlock().getType();
        if(mat == Material.WATER) {
            event.getPlayer().damage(plugin.getDamageAmount());
        }
    }

    @EventHandler
    public void inRain(PlayerMoveEvent event) {
        if(!plugin.getAcidicWater()) return;

        Player player = event.getPlayer();

        if(!player.getWorld().isThundering() && !player.getWorld().hasStorm()) return;
        int blockLocation = player.getLocation().getWorld().getHighestBlockYAt(player.getLocation());
        if(blockLocation <= player.getLocation().getY()) {
            player.damage(plugin.getDamageAmount());
        }
    }

}
