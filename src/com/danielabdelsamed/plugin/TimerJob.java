package com.danielabdelsamed.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
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
//                        for (int j = 0; j <3 ; j++) {
//                            try {
//                                TimeUnit.SECONDS.sleep(10);
//                                player.sendMessage(ChatColor.RED + "#Staff" + ChatColor.GREEN + "Pay your dues...");
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }//for

//                        try {
//                            TimeUnit.SECONDS.sleep(10);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();}
//
//                        player.sendMessage(ChatColor.RED + "#Staff" + ChatColor.GREEN + "You had your chance...");
//
//                        try {
//                            TimeUnit.SECONDS.sleep(10);
//                            player.sendMessage(ChatColor.RED + "#Staff" + ChatColor.GREEN + "Pay your dues...");
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }


                        Bukkit.getScheduler().runTask(plugin, new Runnable() {
                            @Override
                            public void run() {

                                for(int i = 0; i < 100; i++){
                                    player.getWorld().createExplosion(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ(), 5, false, false);
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
