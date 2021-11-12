package me.pray.globalevents.commands.tests;

import me.pray.globalevents.GlobalEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DoubleMobDropTest implements CommandExecutor {

    private GlobalEvents plugin;

    public DoubleMobDropTest(GlobalEvents plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.isOp()) {
            if(plugin.getDoubleXp()) {
                plugin.setDoubleMobDrops(false);
                sender.sendMessage("Stopped testing double mob drops event");
                return true;
            }

            plugin.setDoubleMobDrops(true);
            sender.sendMessage("Starting testing double mob drops event");
        }

        return false;
    }
}
