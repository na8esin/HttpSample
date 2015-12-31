package com.example.watanabe_ta.myapplication;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by watanabe_ta on 2015/12/25.
 */
public class Network {

    public StringBuffer login(){

        String strUrl = "http://blog-imgs-90.fc2.com/n/a/8/na8esin/loginjson.txt";

        HttpURLConnection conn = null;
        InputStream in = null;
        StringBuffer responseJSON = null;
        try {
            conn = (HttpURLConnection) new URL(strUrl).openConnection();
            conn.connect();
            if( conn.getResponseCode() == HttpURLConnection.HTTP_OK ){
                responseJSON = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    Log.d("Network login",inputLine);
                    responseJSON.append(inputLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseJSON;
    }
}
