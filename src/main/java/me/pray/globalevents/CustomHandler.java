package me.pray.globalevents;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.session.MoveType;
import com.sk89q.worldguard.session.Session;
import com.sk89q.worldguard.session.handler.FlagValueChangeHandler;
import com.sk89q.worldguard.session.handler.Handler;

public class CustomHandler extends FlagValueChangeHandler<State> {
    public static final Factory FACTORY = new Factory();

    public static class Factory extends Handler.Factory<CustomHandler> {

        public CustomHandler create(Session session) {
            return new CustomHandler(session);
        }
    }

    public CustomHandler(Session session) {
        super(session, Flags.PVP);
    }

    @Override
    protected void onInitialValue(LocalPlayer player, ApplicableRegionSet set, State value) {//unused
    }

    @Override
    protected boolean onSetValue(LocalPlayer player, Location from, Location to, ApplicableRegionSet toSet,
                                 State currentValue, State lastValue, MoveType moveType) {

        UUID uuid = player.getUniqueId();
        Player p = Bukkit.getPlayer(uuid);
        GlobalEvents plugin = GlobalEvents.getInstance();

        switch (currentValue) {
            case ALLOW:
                if(!plugin.getEvents().isAngelEvent()) return true;
                if(p.hasPotionEffect(PotionEffectType.SPEED) && p.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
                    p.removePotionEffect(PotionEffectType.SPEED);
                    p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                }
                break;
            case DENY:
                if(!plugin.getEvents().isAngelEvent()) return true;
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
                break;
        }
        return true;
    }

    @Override
    protected boolean onAbsentValue(LocalPlayer player, Location from, Location to, ApplicableRegionSet toSet,
                                    State lastValue, MoveType moveType) {//unused
        return false;
    }
}
