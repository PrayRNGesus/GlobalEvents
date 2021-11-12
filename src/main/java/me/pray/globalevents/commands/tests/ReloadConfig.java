package me.pray.globalevents.commands.tests;

import me.pray.globalevents.GlobalEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfig implements CommandExecutor {

    private GlobalEvents plugin;

    public ReloadConfig(GlobalEvents plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("globalevents.reload")) {
            plugin.reloadConfig();
            sender.sendMessage(plugin.format("&aConfig reloaded"));
        } else {
            sender.sendMessage(plugin.format("&cNo permission."));
        }

        return true;
    }
}
