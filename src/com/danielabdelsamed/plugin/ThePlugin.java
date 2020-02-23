/*
    2/22/2020
    Revolution UC
 */
package com.danielabdelsamed.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class ThePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        Timer timer = new Timer();
        timer.schedule(new TimerJob(this), 0, 5000);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }


    @EventHandler(priority= EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        Player player = e.getPlayer();

        Location bLoc= block.getLocation();

        String blockName = block.getType().toString();
        String uuid = player.getUniqueId().toString();
        int x = bLoc.getBlockX();
        int y = bLoc.getBlockY();
        int z = bLoc.getBlockZ();
    }


}


