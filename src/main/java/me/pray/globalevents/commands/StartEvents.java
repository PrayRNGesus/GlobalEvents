package me.pray.globalevents.commands;

import me.pray.globalevents.customevents.StartGlobalEvent;
import me.pray.globalevents.GlobalEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartEvents implements CommandExecutor {

    private GlobalEvents plugin;

    public StartEvents(GlobalEvents plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("globalevents.start")) {
            if(plugin.getRunning()) {
                sender.sendMessage(plugin.format("&cAn event is already running!"));
                return true;
            }

            StartGlobalEvent handler = new StartGlobalEvent(plugin);
            handler.startGlobalEvents();
            plugin.setRunning(true);
        } else {
            sender.sendMessage(plugin.format("&cNo permission."));
        }

        return true;
    }
}
