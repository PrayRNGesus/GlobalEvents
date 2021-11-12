package me.pray.globalevents.commands.tests;

import me.pray.globalevents.GlobalEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DoubleXpTest implements CommandExecutor {

    private GlobalEvents plugin;

    public DoubleXpTest(GlobalEvents plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.isOp()) {
            if(plugin.getDoubleXp()) {
                plugin.setDoubleXp(false);
                sender.sendMessage("Stopped testing double xp event");
                return true;
            }

            plugin.setDoubleXp(true);
            sender.sendMessage("Starting testing double xp event");
        }

        return false;
    }
}
