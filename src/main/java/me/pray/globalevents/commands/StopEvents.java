package me.pray.globalevents.commands;

import me.pray.globalevents.GlobalEvents;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StopEvents implements CommandExecutor {

    private GlobalEvents plugin;

    public StopEvents(GlobalEvents plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("globalevents.stop")) {
            if(!plugin.getRunning()) {
                sender.sendMessage(plugin.format("&cAn event is not running!"));
                return true;
            }

            plugin.getTaskId().forEach(task -> {
                Bukkit.getScheduler().cancelTask(task);
                System.out.println("Task cancelled");
            });

            sender.sendMessage(plugin.format("&cTasks have been ended. "));
        } else {
            sender.sendMessage(plugin.format("&cNo permission."));
        }

        return true;
    }
}
