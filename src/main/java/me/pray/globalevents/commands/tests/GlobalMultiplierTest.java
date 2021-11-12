package me.pray.globalevents.commands.tests;

import me.pray.globalevents.GlobalEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GlobalMultiplierTest implements CommandExecutor {

    private GlobalEvents plugin;

    public GlobalMultiplierTest(GlobalEvents plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.isOp()) {
            if(plugin.getGlobalMultiplierBool()) {
                plugin.setGlobalMultiplierBool(false);
                sender.sendMessage("Stopped testing global multiplier event");
                return true;
            }

            plugin.setGlobalMultiplierBool(true);
            sender.sendMessage("Starting testing global multiplier event");
        }

        return false;
    }
}
