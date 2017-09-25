package com.gmail.vsyniakin.task2;

import java.io.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ParseJSONFile {
    public static Human loadFromJSON (File file){
        Gson gson = new Gson();
        Human human = null;
        try {
            human = gson.fromJson(new FileReader(file), Human.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return human;
    }

    public static void saveToJSONFile (Human human, File file){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String gsonStr = gson.toJson(human);

        try (PrintWriter pw = new PrintWriter(file)){
            pw.println(gsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
