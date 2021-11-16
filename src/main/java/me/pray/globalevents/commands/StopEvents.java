package me.pray.globalevents.commands;

import me.pray.globalevents.GlobalEvents;
import me.pray.globalevents.customevents.Events;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StopEvents implements CommandExecutor {

    private final GlobalEvents plugin;

    public StopEvents(GlobalEvents plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Events events = new Events(plugin);

        if (sender.hasPermission("globalevents.stop")) {
            if (!plugin.getRunning()) {
                sender.sendMessage(plugin.format("&cAn event is not running!"));
                return true;
            }

            plugin.getTaskId().forEach(task -> {
                Bukkit.getScheduler().cancelTask(task);
            });

            Bukkit.getScheduler().cancelTask(plugin.getMainTask());
            plugin.setMainTask(0);


            if (plugin.getRunningType() != null) {
                switch (plugin.getRunningType().toLowerCase()) {
                    case "doubleores":
                        events.endDoubleOres();
                        break;
                    case "doublexp":
                        events.endDoubleXp();
                        break;
                    case "doublemobdrops":
                        events.endDoubleMobDrops();
                        break;
                    case "instakill":
                        events.endInstaKill();
                        break;
                    case "angelevent":
                        events.endAngleEvent();
                        break;
                }
            }


            plugin.setRunning(false);
            sender.sendMessage(plugin.format("&cTasks have been ended. "));
        } else {
            sender.sendMessage(plugin.format("&cNo permission."));
        }

        return true;
    }
}
