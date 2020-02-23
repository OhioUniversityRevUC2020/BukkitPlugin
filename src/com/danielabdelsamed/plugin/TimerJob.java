package com.danielabdelsamed.plugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import static org.bukkit.Bukkit.getLogger;

public class TimerJob extends TimerTask {
    @Override
    public void run() {
        List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
        for(Player player : list){
            getLogger().info(player.getUniqueId().toString());
        }

    }
}
