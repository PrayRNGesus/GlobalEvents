package me.pray.globalevents;

import me.pray.globalevents.commands.ReloadConfig;
import me.pray.globalevents.commands.StartEvents;
import me.pray.globalevents.commands.StopEvents;
import me.pray.globalevents.customevents.Events;
import me.pray.globalevents.customevents.StartGlobalEvent;
import me.pray.globalevents.eventlisteners.*;
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

    private final double version = 1.1;

    private final ArrayList<Integer> taskId = new ArrayList<>();
    private final ConsoleCommandSender console = getServer().getConsoleSender();
    private File configf;
    private FileConfiguration config;
    private boolean running = false;
    private String runningType = null;

    private int mainTask = 0;

    @Override
    public void onEnable() {
        console.sendMessage(format("&5[GlobalEvents] &f- &dBy &6PrayRNGesus &d- started successfully, version: &6" + version));
        createFiles();

        initCmdsAndEvents();

        StartGlobalEvent handler = new StartGlobalEvent(this);
        handler.startGlobalEvents();
        setRunning(true);
    }

    private void initCmdsAndEvents() {
        Events e = new Events(this);
        getServer().getPluginManager().registerEvents(e, this);
        getServer().getPluginManager().registerEvents(new AngelEvent(e), this);
        getServer().getPluginManager().registerEvents(new DoubleMobDrops(e), this);
        getServer().getPluginManager().registerEvents(new DoubleOreEvent(e), this);
        getServer().getPluginManager().registerEvents(new DoubleXp(e), this);
        getServer().getPluginManager().registerEvents(new InstaKill(e), this);

        getCommand("startevents").setExecutor(new StartEvents(this));
        getCommand("stopevents").setExecutor(new StopEvents(this));
        getCommand("globaleventsreload").setExecutor(new ReloadConfig(this));
    }


    public String format(String string) {
        return string.replace("&", "§");
    }

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
}