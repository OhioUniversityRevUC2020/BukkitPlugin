package com.danielabdelsamed.plugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

import com.google.gson.Gson;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

import javax.swing.plaf.synth.Region;

import static org.bukkit.Bukkit.getLogger;

class Payload {
   public ArrayList<String> outOfCredits;

    public static class BlockBreak {
    }
}

class CurrentUsersResponse {
    public boolean success;
    public Payload payload;
}

class TheBlock{
    public int x;
    public int y;
    public int z;
    public String material;
    public String uuid;

    TheBlock(int x, int y, int z, String material, String uuid){
        this.x=x;
        this.y=y;
        this.z=z;
        this.material=material;
        this.uuid=uuid;
    }
}


public class HttpConnection {

    public ArrayList<String> sendCurrentUsers(ArrayList<Player> players) throws IOException {
        URL url = new URL("http://api.rev-uc-2020.asq.digital/plugin/current-players");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
        ArrayList<String> ids = new ArrayList<String>();
        for(Player player: players){
            ids.add(player.getUniqueId().toString());
        }

        Gson gson = new Gson();
        String json = gson.toJson(ids);
        getLogger().info(json);
        osw.write(json);
        osw.flush();
        osw.close();

        int code = con.getResponseCode();

        if (code == HttpURLConnection.HTTP_OK) { //works
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            // print result
            CurrentUsersResponse res = gson.fromJson(in, CurrentUsersResponse.class);

            return res.payload.outOfCredits;
        } else {
            throw new Error("POST Error");
        }
    }//sendPost


    public TheBlock sendBrokenBlock(int x, int y, int z, String material, String uuid) throws IOException {
        URL url = new URL("http://api.rev-uc-2020.asq.digital/plugin/current-block");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);


        TheBlock gg= new TheBlock(x,y,z,material,uuid);

        Gson gson = new Gson();
        String json = gson.toJson(gg);

        getLogger().info(json);
        osw.write(json);
        osw.flush();
        osw.close();

        int code = con.getResponseCode();

        if (code == HttpURLConnection.HTTP_OK) { //works
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            // print result
            TheBlock res = gson.fromJson(in, TheBlock.class);

            return res;
        } else {
            throw new Error("POST Error");
        }
    }
}//com.danielabdelsamed.plugin.HttpConnection
