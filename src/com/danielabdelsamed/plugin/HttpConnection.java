package com.danielabdelsamed.plugin;

import org.bukkit.entity.Player;

import java.io.*;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.bukkit.Bukkit.getLogger;

class Payload {
   public ArrayList<String> outOfCredits;
}

class CurrentUsersResponse {
    public boolean success;
    public Payload payload;
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

}//com.danielabdelsamed.plugin.HttpConnection
