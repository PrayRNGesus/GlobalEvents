package me.pray.globalevents.customevents;

import me.pray.globalevents.GlobalEvents;
import org.bukkit.Bukkit;

public class StartGlobalEvent  {

    GlobalEvents plugin;
    Events events;

    public StartGlobalEvent(GlobalEvents plugin, Events events) {
       this.plugin = plugin;
       this.events = events;
    }

    public void startGlobalEvents() {

        //tick based, each second is 20 ticks so 20 ticks * 3600 seconds = 72000 ticks
        long initialDelay = plugin.getConfig().getLong("server-startup.first-event-delay");
        long eventDelay = plugin.getConfig().getLong("server-startup.event-delay");

        boolean broadcast = plugin.getConfig().getBoolean("server-startup.first-event-broadcast-enabled");

        if (broadcast) {
            String announcementMsg = plugin.getConfig().getString("server-startup.first-event-broadcast");
            plugin.getServer().broadcastMessage(plugin.format(announcementMsg));
        }

        plugin.setMainTask(Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            final long goodEventDuration = plugin.getConfig().getLong("server-startup.good-event-duration");

            @Override
            public void run() {
                checkCurrentEvent();
                String goodEventType = events.getGoodEvent();
                switch (goodEventType.toLowerCase()) {
                    case "double ores":
                        events.startDoubleOres(goodEventDuration);
                        events.setCurrentGoodEvent(events.getCurrentGoodEvent() + 1);
                        break;
                    case "double xp":
                        events.startDoubleXp(goodEventDuration);
                        events.setCurrentGoodEvent(events.getCurrentGoodEvent() + 1);
                        break;
                    case "double mobdrops":
                        events.startDoubleMobDrops(goodEventDuration);
                        events.setCurrentGoodEvent(events.getCurrentGoodEvent() + 1);
                        break;
                    case "insta kill":
                        events.startInstaKill(goodEventDuration);
                        events.setCurrentGoodEvent(events.getCurrentGoodEvent() + 1);
                        break;
                    case "angel event":
                        events.startAngelEvent(goodEventDuration);
                        events.setCurrentGoodEvent(0);
                        break;
                    default:
                        break;
                }
            }
        }, initialDelay, eventDelay));
    }

    private void checkCurrentEvent() {
        if (plugin.getRunningType() != null) {
            if (plugin.getConfig().getBoolean("server-startup.broadcast-event-running")) {
                Bukkit.broadcastMessage(plugin.format("&cNext event did not start as an event is already running!"));
            }

            return;
        }
    }

}
