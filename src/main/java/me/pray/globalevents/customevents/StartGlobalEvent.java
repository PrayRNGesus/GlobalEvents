package me.pray.globalevents.customevents;

import me.pray.globalevents.GlobalEvents;
import org.bukkit.Bukkit;

public class StartGlobalEvent {
    //main class instance
    private GlobalEvents plugin;
    Events e;

    public StartGlobalEvent(GlobalEvents plugin) {
        this.plugin = plugin;
        e = new Events(plugin);
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

            plugin.addToTaskId(Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                @Override
                public void run() {
                    int random = (int) (Math.random()*2);
                    switch (random) {
                        case 0:
                            String goodEventType = getGoodEvent();
                            switch (goodEventType.toLowerCase()) {
                                case "double ores":
                                    e.startDoubleOres(24000L);
                                    break;
                                case "double xp":
                                    e.startDoubleXp(24000L);
                                    break;
                                case "double mobdrops":
                                    e.startDoubleMobDrops(24000L);
                                    break;
                                case "global sell-multiplier":
                                    e.startGlobalMultiplier(24000L);
                                    break;
                                case "angel event":
                                    e.startAngelEvent(24000L);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 1:
                            String badEventType = getBadEvent();
                            switch (badEventType.toLowerCase()) {
                                case "acidic water":
                                    e.startAcidicWater(12000L);
                                    break;
                                case "double mob damage":
                                    e.startDoubleMobDamage(12000L);
                                    break;
                                default:
                                    break;
                            }
                    }
                }
            }, initialDelay, eventDelay));
    }

    public String getGoodEvent() {
        int index = (int) (Math.random() * plugin.goodEvents.size());
        return plugin.goodEvents.get(index);
    }

    public String getBadEvent() {
        int index = (int) (Math.random() * plugin.badEvents.size());
        return plugin.badEvents.get(index);
    }


}
