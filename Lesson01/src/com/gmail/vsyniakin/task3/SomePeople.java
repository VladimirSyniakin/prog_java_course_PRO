package com.gmail.vsyniakin.task3;

public class SomePeople {

    @Save
    private String name;

    @Save
    private int age;

    @Save
    private String profession;


    public SomePeople() {
    }

    public SomePeople(String name, int age, String profession) {
        this.name = name;
        this.age = age;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getProfession() {
        return profession;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
