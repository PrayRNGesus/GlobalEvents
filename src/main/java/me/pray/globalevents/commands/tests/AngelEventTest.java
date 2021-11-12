package me.pray.globalevents.commands.tests;

import me.pray.globalevents.GlobalEvents;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AngelEventTest implements CommandExecutor{

    private GlobalEvents plugin;

    public AngelEventTest(GlobalEvents plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.isOp()) {
            if(plugin.getAngelEvent()) {
                plugin.setAngelEvent(false);
                sender.sendMessage("Stopped testing angel event");
                for(Player p : Bukkit.getOnlinePlayers()) {
                    p.removePotionEffect(PotionEffectType.SPEED);
                    p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                }
                return true;
            }

            plugin.setAngelEvent(true);
            sender.sendMessage("Starting testing angel event");
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
            }
        }

        return false;
    }
}
