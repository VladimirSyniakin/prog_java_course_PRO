package com.gmail.vsyniakin.task1;

public class TestAnnotationClass {

    @TestAnnotation(a = 3, b = 7)
    public void test(int a, int b) {
        System.out.println("testAnnotation, sum a + b: " + (a + b));
    }

}
