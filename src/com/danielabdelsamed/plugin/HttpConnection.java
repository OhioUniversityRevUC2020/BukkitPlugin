package com.danielabdelsamed.plugin;

import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class Payload {
   public ArrayList<String> outOfCredits;
}

class CurrentUsersResponse {
    public boolean success;
    public Payload payload;
}

public class HttpConnection {

    public ArrayList<String> sendCurrentUsers(ArrayList<Player> players) throws IOException {
        URL url = new URL("http://localhost:8080/test");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        ArrayList<String> ids = new ArrayList<String>();
        for(Player player: players){
            ids.add(player.getUniqueId().toString());
        }

        Gson gson = new Gson();
        os.write(gson.toJson(ids).getBytes());
        os.flush();
        os.close();

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
