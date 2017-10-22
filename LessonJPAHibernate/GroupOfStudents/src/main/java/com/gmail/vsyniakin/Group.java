package com.gmail.vsyniakin;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@NamedQueries({
        @NamedQuery(name = "Group.searchByName", query = "SELECT g FROM Group g WHERE g.name = :name"),
        @NamedQuery(name = "Group.showAllGroups", query = "SELECT g FROM Group g")
})
public class Group {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setGroup(this);
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students.size() +
                '}';
    }
}
