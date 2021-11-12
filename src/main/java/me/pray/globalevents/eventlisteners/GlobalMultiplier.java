package me.pray.globalevents.eventlisteners;

import me.pray.globalevents.GlobalEvents;
import me.pray.globalevents.customevents.Events;
import net.brcdev.shopgui.event.ShopPreTransactionEvent;
import net.brcdev.shopgui.shop.ShopManager;
import org.bukkit.event.EventHandler;

public class GlobalMultiplier extends Events {

    public GlobalMultiplier(GlobalEvents plugin) {
        super(plugin);
    }

    @EventHandler
    public void onShopTrans(ShopPreTransactionEvent event) {
        if (!plugin.getGlobalMultiplierBool()) return;
        if(event.getShopAction().equals(ShopManager.ShopAction.SELL) || event.getShopAction().equals(ShopManager.ShopAction.SELL_ALL)) {
            event.setPrice(event.getPrice() * plugin.getGlobalMultiplierAmount());
        }
    }
}
