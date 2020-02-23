/*
    2/22/2020
    Revolution UC
 */
package com.danielabdelsamed.plugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class ThePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        Timer timer = new Timer();
        timer.schedule(new TimerJob(), 0, 5000);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


}

