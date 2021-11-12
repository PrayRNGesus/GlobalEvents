package me.pray.globalevents.commands.tests;

import me.pray.globalevents.GlobalEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DoubleDropTest implements CommandExecutor{

    private GlobalEvents plugin;

    public DoubleDropTest(GlobalEvents plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.isOp()) {
            if(plugin.getDoubleOres()) {
                plugin.setDoubleOres(false);
                sender.sendMessage("Stopped testing double ores");
                return true;
            }

                plugin.setDoubleOres(true);
                sender.sendMessage("Starting testing double ores");
        }

        return false;
    }
}
