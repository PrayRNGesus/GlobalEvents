package me.pray.globalevents.eventlisteners;

import me.pray.globalevents.GlobalEvents;
import me.pray.globalevents.customevents.Events;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class DoubleOreEvent extends Events {

    public DoubleOreEvent(GlobalEvents plugin) {
        super(plugin);
    }

    @EventHandler
    public void doubleOreEvent(BlockBreakEvent event) {
        if (!plugin.getDoubleOres()) return;
        if (event.getPlayer().getGameMode() != GameMode.SURVIVAL) return;
        Location loc = event.getBlock().getLocation();
        Player p = event.getPlayer();
        Block block = event.getBlock();
        ItemStack mainHand = p.getInventory().getItemInMainHand();

        switch (event.getBlock().getType()) {
            case DIAMOND_ORE:
                if (mainHand.containsEnchantment(Enchantment.SILK_TOUCH)) {
                    ItemStack diamonds = new ItemStack(Material.DIAMOND_ORE, 1);
                    block.getWorld().dropItemNaturally(loc, diamonds);
                    break;
                }

                ItemStack diamonds = new ItemStack(Material.DIAMOND, 1);
                block.getWorld().dropItemNaturally(loc, diamonds);
                break;
            case IRON_ORE:
                ItemStack iron = new ItemStack(Material.IRON_ORE, 1);
                block.getWorld().dropItemNaturally(loc, iron);
                break;
            case COAL_ORE:
                if (mainHand.containsEnchantment(Enchantment.SILK_TOUCH)) {
                    ItemStack coal = new ItemStack(Material.COAL_ORE, 1);
                    block.getWorld().dropItemNaturally(loc, coal);
                    break;
                }

                ItemStack coal = new ItemStack(Material.COAL, 1);
                block.getWorld().dropItemNaturally(loc, coal);
                break;
            case LAPIS_ORE:
                if (mainHand.containsEnchantment(Enchantment.SILK_TOUCH)) {
                    ItemStack lapis = new ItemStack(Material.LAPIS_ORE, 1);
                    block.getWorld().dropItemNaturally(loc, lapis);
                    break;
                }

                ItemStack lapis = new ItemStack(Material.LAPIS_LAZULI, 7);
                block.getWorld().dropItemNaturally(loc, lapis);
                break;
            case EMERALD_ORE:
                if (mainHand.containsEnchantment(Enchantment.SILK_TOUCH)) {
                    ItemStack emerald = new ItemStack(Material.EMERALD_ORE, 1);
                    block.getWorld().dropItemNaturally(loc, emerald);
                    break;
                }

                ItemStack emerald = new ItemStack(Material.EMERALD, 1);
                block.getWorld().dropItemNaturally(loc, emerald);
                break;
            case REDSTONE_ORE:
                if (mainHand.containsEnchantment(Enchantment.SILK_TOUCH)) {
                    ItemStack redstone = new ItemStack(Material.REDSTONE_ORE, 1);
                    block.getWorld().dropItemNaturally(loc, redstone);
                    break;
                }

                ItemStack redstone = new ItemStack(Material.REDSTONE, 4);
                block.getWorld().dropItemNaturally(loc, redstone);
                break;
            case GOLD_ORE:
                ItemStack gold = new ItemStack(Material.GOLD_ORE, 1);
                block.getWorld().dropItemNaturally(loc, gold);
                break;
            case ANCIENT_DEBRIS:
                ItemStack ancientDebris = new ItemStack(Material.ANCIENT_DEBRIS, 1);
                block.getWorld().dropItemNaturally(loc, ancientDebris);
                break;
        }

    }

}
