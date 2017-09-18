package com.gmail.vsyniakin.task2;

import java.io.*;

@SaveTo(fileName = "textTemp.txt")
public class TextContainer {

    private String text = "I will save this text!";

    @Saver
        public void save (String fileName){
        try (BufferedWriter bfW = new BufferedWriter(new FileWriter(new File(fileName)))) {
            bfW.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
