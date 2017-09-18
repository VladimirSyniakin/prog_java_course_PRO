package com.gmail.vsyniakin;

import com.gmail.vsyniakin.task2.SaveTo;
import com.gmail.vsyniakin.task2.Saver;
import com.gmail.vsyniakin.task2.TextContainer;
import com.gmail.vsyniakin.task1.TestAnnotation;
import com.gmail.vsyniakin.task1.TestAnnotationClass;
import com.gmail.vsyniakin.task3.SerializeWithAnnotation;
import com.gmail.vsyniakin.task3.SomePeople;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main (String [] args) throws InvocationTargetException, IllegalAccessException {


        // Task01:
        Class <?> myTestClass = TestAnnotationClass.class;

        try {
            Method [] methods = myTestClass.getMethods();
            for (Method method:methods) {
                if (method.isAnnotationPresent(TestAnnotation.class)){
                    TestAnnotation annotation = method.getAnnotation(TestAnnotation.class);
                    method.invoke(new TestAnnotationClass(),annotation.a(),annotation.b());
                }
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }


        // Task02:
        TextContainer textContainer = new TextContainer();

        Class<?> conteinerClass = textContainer.getClass();

        SaveTo saveTo = conteinerClass.getAnnotation(SaveTo.class);

        Method [] methodArray = conteinerClass.getDeclaredMethods();
        for (Method method: methodArray) {
            if (method.isAnnotationPresent(Saver.class)){
                method.invoke(textContainer, saveTo.fileName());

            }
        }


        //Task03:
        SomePeople joni = new SomePeople("Johnnie Walker", 12, "Friday friend");
        SerializeWithAnnotation.serializableFields(joni,"fileJoni.txt");
        System.out.println(SerializeWithAnnotation.deserializableFields(new File("fileJoni.txt")));
    }
}


