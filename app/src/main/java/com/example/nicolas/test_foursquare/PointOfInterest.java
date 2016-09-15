package com.example.nicolas.test_foursquare;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;

/**
 * Created by Nicolas on 15/09/2016.
 */
public class PointOfInterest {
    private String id = "-1" ;
    private String name;
    private Location address;



    public PointOfInterest(String id, String name, Location address) {
        this.id = id;
        this.name = name;
        this.address = address;

    }


    public String getName() {
        return name;
    }

    public Location getAddress() {
        return address;
    }

    public static String readFile(String filename) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
