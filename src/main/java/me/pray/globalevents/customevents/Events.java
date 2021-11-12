package me.pray.globalevents.customevents;

import me.pray.globalevents.GlobalEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Events implements Listener {

    public final GlobalEvents plugin;

    public Events(GlobalEvents plugin) {
        this.plugin = plugin;
    }

    public void startDoubleOres(Long durationInTicks) {
        plugin.setDoubleOres(true);

        String doubleOresMsg = plugin.getConfig().getString("server-event-broadcasts.double-ores.start-msg");
        plugin.getServer().broadcastMessage(plugin.format(doubleOresMsg));

        plugin.addToTaskId(Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                String endMessage = plugin.getConfig().getString("server-event-broadcasts.double-ores.end-msg");
                plugin.setDoubleOres(false);
                plugin.getServer().broadcastMessage(plugin.format(endMessage));
            }
        }, durationInTicks));
    }

    public void startDoubleXp(Long durationInTicks) {
        plugin.setDoubleXp(true);

        String doubleXpMsg = plugin.getConfig().getString("server-event-broadcasts.double-xp.start-msg");
        plugin.getServer().broadcastMessage(plugin.format(doubleXpMsg));

        plugin.addToTaskId(Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                String endMessage = plugin.getConfig().getString("server-event-broadcasts.double-xp.end-msg");
                plugin.setDoubleXp(false);
                plugin.getServer().broadcastMessage(plugin.format(endMessage));
            }
        }, durationInTicks));
    }

    public void startDoubleMobDrops(Long durationInTicks) {
        plugin.setDoubleMobDrops(true);

        String doubleXpMsg = plugin.getConfig().getString("server-event-broadcasts.double-mob-drops.start-msg");
        plugin.getServer().broadcastMessage(plugin.format(doubleXpMsg));

        plugin.addToTaskId(Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                String endMessage = plugin.getConfig().getString("server-event-broadcasts.double-mob-drops.end-msg");
                plugin.setDoubleMobDrops(false);
                plugin.getServer().broadcastMessage(plugin.format(endMessage));
            }
        }, durationInTicks));
    }

    public void startGlobalMultiplier(Long durationInTicks) {
        plugin.setGlobalMultiplierBool(true);

        String multiplierMsg = plugin.getConfig().getString("server-event-broadcasts.global-multiplier.start-msg");
        plugin.getServer().broadcastMessage(plugin.format(multiplierMsg));

        plugin.addToTaskId(Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                String endMessage = plugin.getConfig().getString("server-event-broadcasts.global-multiplier.end-msg");
                plugin.setGlobalMultiplierBool(false);
                plugin.getServer().broadcastMessage(plugin.format(endMessage));
            }
        }, durationInTicks));
    }

    public void startAngelEvent(Long durationInTicks) {
        plugin.setAngelEvent(true);

        //had to do direct
        plugin.getServer().broadcastMessage(plugin.format(plugin.getConfig().getString("server-event-broadcasts.angle-event.start-msg")));

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
        }

        plugin.addToTaskId(Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                String endMessage = plugin.getConfig().getString("server-event-broadcasts.angel-event.end-msg");
                plugin.setAngelEvent(false);
                plugin.getServer().broadcastMessage(plugin.format(endMessage));
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.removePotionEffect(PotionEffectType.SPEED);
                    p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                }
            }
        }, durationInTicks));
    }

    public void startAcidicWater(Long durationInTicks) {
        plugin.setAcidicWater(true);


        String warningMsg = plugin.getConfig().getString("server-event-broadcasts.acidic-water.warning-msg");
        plugin.getServer().broadcastMessage(plugin.format(warningMsg));

        Bukkit.getScheduler().runTaskLater(plugin, () -> {

            String startMsg = plugin.getConfig().getString("server-event-broadcasts.acidic-water.start-msg");
            plugin.getServer().broadcastMessage(plugin.format(startMsg));

            plugin.addToTaskId(Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    String endMessage = plugin.getConfig().getString("server-event-broadcasts.acidic-water.end-msg");
                    plugin.setAcidicWater(false);
                    plugin.getServer().broadcastMessage(plugin.format(endMessage));
                }
            }, durationInTicks));
        }, 60L);

    }

    public void startDoubleMobDamage(Long durationInTicks) {
        plugin.setDoubleMobDamage(true);

        String startMsg = plugin.getConfig().getString("server-event-broadcasts.double-mob-damage.start-msg");
        plugin.getServer().broadcastMessage(plugin.format(startMsg));

        plugin.addToTaskId(Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                String endMessage = plugin.getConfig().getString("server-event-broadcasts.double-mob-damage.end-msg");
                plugin.setDoubleMobDamage(false);
                plugin.getServer().broadcastMessage(plugin.format(endMessage));
            }
        }, durationInTicks));

    }
}