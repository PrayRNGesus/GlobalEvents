package me.pray.globalevents.customevents;

import me.pray.globalevents.GlobalEvents;
import org.bukkit.Bukkit;

public class StartGlobalEvent extends Events {

    public StartGlobalEvent(GlobalEvents plugin) {
        super(plugin);
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
                String goodEventType = getGoodEvent();
                switch (goodEventType.toLowerCase()) {
                    case "double ores":
                        startDoubleOres(goodEventDuration);
                        setCurrentGoodEvent(getCurrentGoodEvent() + 1);
                        break;
                    case "double xp":
                        startDoubleXp(goodEventDuration);
                        setCurrentGoodEvent(getCurrentGoodEvent() + 1);
                        break;
                    case "double mobdrops":
                        startDoubleMobDrops(goodEventDuration);
                        setCurrentGoodEvent(getCurrentGoodEvent() + 1);
                        break;
                    case "insta kill":
                        startInstaKill(goodEventDuration);
                        setCurrentGoodEvent(getCurrentGoodEvent() + 1);
                        break;
                    case "angel event":
                        startAngelEvent(goodEventDuration);
                        setCurrentGoodEvent(0);
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
