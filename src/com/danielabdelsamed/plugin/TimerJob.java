package com.danielabdelsamed.plugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import static org.bukkit.Bukkit.getLogger;

public class TimerJob extends TimerTask {

    private Plugin plugin;

    public TimerJob(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        HttpConnection http = new HttpConnection();
        try{
            ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
            ArrayList<String> ids = http.sendCurrentUsers(players);
            for(String id : ids){
                getLogger().info("Player " + id + " is out of credits! Killing!");
                for(Player player : players){
                    if(player.getUniqueId().toString().equals(id)) {
                        Bukkit.getScheduler().runTask(plugin, new Runnable() {
                            @Override
                            public void run() {
                                for(int i = 0; i < 100; i++){
                                    player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);
                                }
                            }
                        });
                    }
                }
            }
        }catch(IOException error) {
            getLogger().info("LMAO youre fucked");
        }


    }
}
