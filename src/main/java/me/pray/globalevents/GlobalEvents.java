package me.pray.globalevents;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.session.SessionManager;
import me.pray.globalevents.Commands.ReloadConfig;
import me.pray.globalevents.Commands.StartEvents;
import me.pray.globalevents.Commands.StopEvents;
import me.pray.globalevents.CustomEvents.Events;
import me.pray.globalevents.CustomEvents.StartGlobalEvent;
import me.pray.globalevents.EventListeners.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GlobalEvents extends JavaPlugin {

    //only used for CustomHandler class
    private static GlobalEvents instance;

    private final double version = 1.1;

    private final ArrayList<Integer> taskId = new ArrayList<>();
    private final ConsoleCommandSender console = getServer().getConsoleSender();
    private File configf;
    private FileConfiguration config;
    private boolean running = false;
    private String runningType = null;

    private int mainTask = 0;

    Events events = new Events(this);

    @Override
    public void onEnable() {
        instance = this;

        console.sendMessage(format("&5[GlobalEvents] &f- &dBy &6PrayRNGesus &d- started successfully, version: &6" + version));
        createFiles();

        initCmdsAndEvents();

        StartGlobalEvent handler = new StartGlobalEvent(this, events);
        handler.startGlobalEvents();
        setRunning(true);

        SessionManager sessionManager = WorldGuard.getInstance().getPlatform().getSessionManager();
        sessionManager.registerHandler(CustomHandler.FACTORY, null);
    }

    private void initCmdsAndEvents() {
        getServer().getPluginManager().registerEvents(events, this);
        getServer().getPluginManager().registerEvents(new AngelEvent(events), this);
        getServer().getPluginManager().registerEvents(new DoubleMobDrops(events), this);
        getServer().getPluginManager().registerEvents(new DoubleOreEvent(events), this);
        getServer().getPluginManager().registerEvents(new DoubleXp(events), this);
        getServer().getPluginManager().registerEvents(new InstaKill(events), this);

        getCommand("startevents").setExecutor(new StartEvents(this, events));
        getCommand("stopevents").setExecutor(new StopEvents(this));
        getCommand("globaleventsreload").setExecutor(new ReloadConfig(this));
    }


    public String format(String string) {
        return string.replace("&", "§");
    }

    //used for config parsing
    public String format(List<String> string) {
        String s = "";
        for (int i = 0; i < string.size(); i++) {
            String st = format(string.get(i));

            if (st.isEmpty()) {
                s += " " + "\n\n";
                continue;
            }

            s += st.replace("&", "§");
            s += " " + "\n";

        }
        return s;
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

    public boolean getRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public ArrayList<Integer> getTaskId() {
        return taskId;
    }

    public void addToTaskId(int newId) {
        taskId.add(newId);
    }

    public int getMainTask() {
        return mainTask;
    }

    public void setMainTask(int mainTask) {
        this.mainTask = mainTask;
    }

    public String getRunningType() {
        return runningType;
    }

    public void setRunningType(String runningType) {
        this.runningType = runningType;
    }

    public Events getEvents() {
        return events;
    }

    public static GlobalEvents getInstance() {
        return instance;
    }

}