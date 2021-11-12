package me.pray.globalevents;

import me.pray.globalevents.commands.StopEvents;
import me.pray.globalevents.commands.tests.*;
import me.pray.globalevents.commands.StartEvents;
import me.pray.globalevents.eventlisteners.*;
import me.pray.globalevents.eventlisteners.DoubleMobDamage;
import me.pray.globalevents.customevents.Events;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class GlobalEvents extends JavaPlugin {

    public File configf;
    public FileConfiguration config;

    public ConsoleCommandSender console = getServer().getConsoleSender();

    private double version = 1.0;

    private boolean doubleOres = false;
    private boolean doubleXp = false;
    private boolean doubleMobDrops = false;
    private boolean globalMultiplier = false;
    private boolean angelEvent = false;
    private boolean acidicWater = false;
    private boolean doubleMobDamage = false;

    public ArrayList<String> goodEvents = new ArrayList<>();
    public ArrayList<String> badEvents = new ArrayList<>();

    private boolean running = false;

    private ArrayList<Integer> taskId = new ArrayList<>();

    @Override
    public void onEnable() {
        console.sendMessage(format("&5[GlobalEvents] &f- &dBy &6PrayRNGesus &d- started successfully, version: &6" + version));
        createFiles();

       initEvents();

       initCmdsAndEvents();
    }

    private void initCmdsAndEvents() {
        getServer().getPluginManager().registerEvents(new Events(this), this);
        getServer().getPluginManager().registerEvents(new AngelEvent(this),this);
        getServer().getPluginManager().registerEvents(new DoubleMobDrops(this), this);
        getServer().getPluginManager().registerEvents(new DoubleOreEvent(this), this);
        getServer().getPluginManager().registerEvents(new GlobalMultiplier(this), this);
        getServer().getPluginManager().registerEvents(new DoubleXp(this), this);
        getServer().getPluginManager().registerEvents(new AcidicWater(this), this);
        getServer().getPluginManager().registerEvents(new DoubleMobDamage(this), this);

        getCommand("startevents").setExecutor(new StartEvents(this));
        getCommand("stopevents").setExecutor(new StopEvents(this));
        getCommand("globaleventsreload").setExecutor(new ReloadConfig(this));

        getCommand("doubleoretest").setExecutor(new DoubleDropTest(this));
        getCommand("angeleventtest").setExecutor(new AngelEventTest(this));
        getCommand("multipliertest").setExecutor(new GlobalMultiplierTest(this));
        getCommand("doublemobdroptest").setExecutor(new DoubleMobDropTest(this));
        getCommand("doublexptest").setExecutor(new DoubleXpTest(this));
        getCommand("acidicwatertest").setExecutor(new AcidicWaterTest(this));
        getCommand("doublemobdamage").setExecutor(new DoubleMobDamageTest(this));
    }


    public String format(String string) {
        return string.replace("&", "§").replace("%newline%", "\n");
    }

    public void createFiles() {
        configf = new File(getDataFolder(), "config.yml");

        if (!configf.exists()) {
            configf.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        config = new YamlConfiguration();

        try {
            config.load(configf);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void initEvents() {
        goodEvents.add("Double Ores");
        goodEvents.add("Double XP");
        goodEvents.add("Double Mobdrops");
        goodEvents.add("Global Sell-Multiplier");
        goodEvents.add("Angel Event");
        badEvents.add("Acidic Water");
        badEvents.add("Double Mob Damage");
    }

    public void setDoubleOres(boolean newValue) {
        doubleOres = newValue;
    }

    public boolean getDoubleOres() {
        return doubleOres;
    }

    public void setDoubleXp(boolean newValue) {
        doubleXp = newValue;
    }

    public boolean getDoubleXp() {
        return doubleXp;
    }

    public void setDoubleMobDrops(boolean newValue) {
        doubleMobDrops = newValue;
    }

    public boolean getDoubleMobDrops() {
        return doubleMobDrops;
    }

    public void setGlobalMultiplierBool(boolean newValue) {
        globalMultiplier = newValue;
    }

    public boolean getGlobalMultiplierBool() {
        return globalMultiplier;
    }

    public double getGlobalMultiplierAmount() {
        return getConfig().getDouble("server-event-broadcasts.global-multiplier.amount");
    }

    public void setAngelEvent(boolean newValue) {
        angelEvent = newValue;
    }

    public boolean getAngelEvent() {
        return angelEvent;
    }

    public boolean getAcidicWater() {
        return acidicWater;
    }

    public void setAcidicWater(boolean newValue) {
        acidicWater = newValue;
    }

    public boolean getRunning() {
        return running;
    }

    public void setRunning(boolean newValue) {
        running = newValue;
    }

    public ArrayList<Integer> getTaskId() {
        return taskId;
    }

    public void addToTaskId(int newId) {
        taskId.add(newId);
    }

    public double getDamageAmount() {
        return getConfig().getDouble("server-event-broadcasts.acidic-water.damage-amount");
    }

    public boolean getDoubleMobDamage() {
        return doubleMobDamage;
    }

    public void setDoubleMobDamage(boolean newValue) {
        doubleMobDamage = newValue;
    }

}