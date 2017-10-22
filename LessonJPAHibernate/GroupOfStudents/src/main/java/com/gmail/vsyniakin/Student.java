package com.gmail.vsyniakin;

import javax.persistence.*;

@Entity
@Table (name = "students")
@NamedQueries( {
        @NamedQuery(name = "Student.searchByName", query = "SELECT s FROM Student s WHERE s.name = :name")
})
public class Student {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String name;
    private int age;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_group")
    private Group group;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        if (group != null) {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", group=" + group.getName() +
                    '}';
        } else {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", group=" + "null" +
                    '}';
        }

    }
}
