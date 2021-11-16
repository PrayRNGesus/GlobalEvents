package me.pray.globalevents.CustomEvents;

import me.pray.globalevents.GlobalEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import java.util.List;

public class Events implements Listener {

    public int currentGoodEvent = 0;
    public boolean doubleOres = false;
    public boolean doubleXp = false;
    public boolean doubleMobDrops = false;
    public boolean angelEvent = false;
    public boolean instaKill = false;

    public String[] goodEvents = {"Double Ores", "Double XP", "Double Mobdrops", "Insta Kill", "Angel Event"};

    public final GlobalEvents plugin;

    public Events(GlobalEvents plugin) {
        this.plugin = plugin;
    }

    public void startDoubleOres(Long durationInTicks) {
        setDoubleOres(true);
        plugin.setRunningType("DoubleOres");

        List<String> doubleOresMsg = plugin.getConfig().getStringList("server-event-broadcasts.double-ores.start-msg");
        plugin.getServer().broadcastMessage(plugin.format(doubleOresMsg));

        plugin.addToTaskId(Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                endDoubleOres();
            }
        }, durationInTicks));
    }

    public void endDoubleOres() {
        List<String> endMessage = plugin.getConfig().getStringList("server-event-broadcasts.double-ores.end-msg");
        setDoubleOres(false);
        plugin.getServer().broadcastMessage(plugin.format(endMessage));
        plugin.setRunningType(null);
    }

    public void startDoubleXp(Long durationInTicks) {
        setDoubleXp(true);
        plugin.setRunningType("DoubleXP");

        List<String> doubleXpMsg = plugin.getConfig().getStringList("server-event-broadcasts.double-xp.start-msg");
        plugin.getServer().broadcastMessage(plugin.format(doubleXpMsg));

        plugin.addToTaskId(Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                endDoubleXp();
            }
        }, durationInTicks));
    }

    public void endDoubleXp() {
        List<String> endMessage = plugin.getConfig().getStringList("server-event-broadcasts.double-xp.end-msg");
        setDoubleXp(false);
        plugin.getServer().broadcastMessage(plugin.format(endMessage));
        plugin.setRunningType(null);
    }

    public void startDoubleMobDrops(Long durationInTicks) {
        setDoubleMobDrops(true);
        plugin.setRunningType("DoubleMobDrops");

        List<String> doubleXpMsg = plugin.getConfig().getStringList("server-event-broadcasts.double-mob-drops.start-msg");
        plugin.getServer().broadcastMessage(plugin.format(doubleXpMsg));

        plugin.addToTaskId(Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                endDoubleMobDrops();
            }
        }, durationInTicks));
    }

    public void endDoubleMobDrops() {
        List<String> endMessage = plugin.getConfig().getStringList("server-event-broadcasts.double-mob-drops.end-msg");
        setDoubleMobDrops(false);
        plugin.getServer().broadcastMessage(plugin.format(endMessage));
        plugin.setRunningType(null);
    }

    public void startInstaKill(Long durationInTicks) {
        setInstaKill(true);
        plugin.setRunningType("InstaKill");

        List<String> startMsg = plugin.getConfig().getStringList("server-event-broadcasts.insta-kill.start-msg");
        plugin.getServer().broadcastMessage(plugin.format(startMsg));

        plugin.addToTaskId(Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                endInstaKill();
            }
        }, durationInTicks));
    }

    public void endInstaKill() {
        List<String> endMessage = plugin.getConfig().getStringList("server-event-broadcasts.insta-kill.end-msg");
        setInstaKill(false);
        plugin.getServer().broadcastMessage(plugin.format(endMessage));
        plugin.setRunningType(null);
    }

    public void startAngelEvent(Long durationInTicks) {
        setAngelEvent(true);
        plugin.setRunningType("AngelEvent");

        //had to do direct
        plugin.getServer().broadcastMessage(plugin.format(plugin.getConfig().getStringList("server-event-broadcasts.angle-event.start-msg")));

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
        }

        plugin.addToTaskId(Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                endAngleEvent();
            }
        }, durationInTicks));
    }

    public void endAngleEvent() {
        List<String> endMessage = plugin.getConfig().getStringList("server-event-broadcasts.angel-event.end-msg");
        setAngelEvent(false);
        plugin.getServer().broadcastMessage(plugin.format(endMessage));
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.removePotionEffect(PotionEffectType.SPEED);
            p.removePotionEffect(PotionEffectType.FAST_DIGGING);
            plugin.setRunningType(null);
        }
    }

    public boolean isDoubleOres() {
        return doubleOres;
    }

    public void setDoubleOres(boolean doubleOres) {
        this.doubleOres = doubleOres;
    }

    public boolean isDoubleXp() {
        return doubleXp;
    }

    public void setDoubleXp(boolean doubleXp) {
        this.doubleXp = doubleXp;
    }

    public boolean isDoubleMobDrops() {
        return doubleMobDrops;
    }

    public void setDoubleMobDrops(boolean doubleMobDrops) {
        this.doubleMobDrops = doubleMobDrops;
    }

    public boolean isAngelEvent() {
        return angelEvent;
    }

    public void setAngelEvent(boolean angelEvent) {
        this.angelEvent = angelEvent;
    }

    public int getCurrentGoodEvent() {
        return currentGoodEvent;
    }

    public void setCurrentGoodEvent(int currentGoodEvent) {
        this.currentGoodEvent = currentGoodEvent;
    }

    public boolean isInstaKill() {
        return instaKill;
    }

    public void setInstaKill(boolean instaKill) {
        this.instaKill = instaKill;
    }

    public String getGoodEvent() {
        int index = getCurrentGoodEvent();
        return goodEvents[index];
    }
}