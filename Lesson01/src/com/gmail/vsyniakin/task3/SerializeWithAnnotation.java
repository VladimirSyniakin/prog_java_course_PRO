package com.gmail.vsyniakin.task3;

import java.io.*;
import java.lang.reflect.Field;


public class SerializeWithAnnotation {

    public static void serializableFields(Object obj, String nameFile) {
        Class<?> tempClass = obj.getClass();
        Field[] fields = tempClass.getDeclaredFields();

        try (BufferedWriter bfWrite = new BufferedWriter(new FileWriter(new File(nameFile)))) {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Save.class)) {
                    bfWrite.write(field.getName() + ": " + field.get(obj));
                    bfWrite.newLine();
                }
            }
        } catch (IllegalAccessException e) {

        } catch (IOException e) {
            System.out.println("Oops! IOException");
        }
    }

    public static String deserializableFields(File file) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader bfReader = new BufferedReader(new FileReader(file))){
            String strTemp;
            while ((strTemp = bfReader.readLine()) != null){

                result.append(strTemp + "\n");

            }
        } catch (IOException e){
            System.out.println("Oops! IOException");
        }
        return result.toString();
    }

}
