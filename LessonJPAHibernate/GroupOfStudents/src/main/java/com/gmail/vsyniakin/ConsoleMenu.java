package com.gmail.vsyniakin;


import javax.persistence.EntityManager;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    public static void getAction(Scanner scanner, EntityManager em) {
        showConsoleMenu();
        String choice = scanner.nextLine();
        if (choice != null) {
            switch (choice) {
                case "0":
                    break;
                case "1":
                    menuAddStudent(scanner, em);
                    getAction(scanner, em);
                    break;
                case "2":
                    menuAddGroup(scanner, em);
                    getAction(scanner, em);
                    break;
                case "3":
                    menuUpdateDB(em);
                    getAction(scanner, em);
                    break;
                case "4":
                    menuChangeStudent(scanner, em);
                    getAction(scanner, em);
                    break;
                case "5":
                    menuDeleteGroup(scanner, em);
                    getAction(scanner, em);
                    break;
                case "6":
                    menuShowGroups(em);
                    getAction(scanner, em);
                    break;
                default:
                    System.out.println("Incorrect choice!");
                    getAction(scanner, em);
                    break;

            }
        } else {
            System.out.println("Incorrect choice!");
        }
    }

    public static void showConsoleMenu() {
        System.out.println("1 - add student");
        System.out.println("2 - add group");
        System.out.println("3 - update database");
        System.out.println("4 - change student data");
        System.out.println("5 - delete group");
        System.out.println("6 - show list of groups");
        System.out.println("0 - end");
    }

    public static Student menuAddStudent(Scanner scanner, EntityManager em) {
        Student student = new Student();

        System.out.println("Enter name:");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            student.setName(name);
        } else {
            System.out.println("No name!");
            return null;
        }
        System.out.println("Enter age:");
        try {
            student.setAge(Integer.parseInt(scanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Incorrect format!");
            return null;
        }
        System.out.println("Enter group name:");
        String nameGroup = scanner.nextLine();
        if (!nameGroup.isEmpty()) {
            Group group = Actions.searchGroupByName(nameGroup, em);
            if (group != null) {
                group.addStudent(student);
            } else {
                System.out.println("Group not found.");
            }
        }

        System.out.println("Add student to database? true/false");
        if (Boolean.parseBoolean(scanner.nextLine())) {
            Actions.addToDB(student, em);
        }
        return student;
    }

    public static Group menuAddGroup(Scanner scanner, EntityManager em) {
        Group group;

        System.out.println("Enter group name:");
        String name = scanner.nextLine();
        if ((!name.isEmpty())&& (Actions.searchGroupByName(name,em)==null)) {
            group = new Group(name);
            Actions.addToDB(group, em);
            return group;
        } else {
            System.out.println("Error.");
            return null;
        }
    }

    public static void menuUpdateDB(EntityManager em) {
        Actions.updateToDB(em);
    }

    public static void menuChangeStudent(Scanner scanner, EntityManager em) {
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        Student student = null;
        if (!name.isEmpty()) {
            student = Actions.searchStudentByName(name, em);
        } else {
            System.out.println("No name!");
            return;
        }
        if (student != null) {
            System.out.println(student);
            System.out.println("Change age, enter age:");
            String age = scanner.nextLine();
            if (!age.isEmpty()) {
                try {
                    student.setAge(Integer.parseInt(age));
                } catch (Exception e) {
                    System.out.println("Incorrect format!");
                }
            }
            System.out.println("Change group, enter group name:");
            String nameGroup = scanner.nextLine();
            if (!nameGroup.isEmpty()) {
                Group group = Actions.searchGroupByName(nameGroup, em);
                if (group != null) {
                    group.addStudent(student);
                } else {
                    student.setGroup(null);
                }
            }
        } else {
            System.out.println("Student not found.");
        }
        Actions.updateStudentToDB(student, em);
    }

    public static void menuDeleteGroup (Scanner scanner, EntityManager em) {
        System.out.println("Enter group name:");
        String nameGroup = scanner.nextLine();
        Group group = null;
        if (!nameGroup.isEmpty()) {
            group = Actions.searchGroupByName(nameGroup,em);
            if (group != null) {
                Actions.deleteGroup(group,em);
            } else {
                System.out.println("Group not found");
            }
        } else {
            System.out.println("No name!");
        }
    }

    public static void menuShowGroups (EntityManager em) {
        List <Group> groups = Actions.selectGroups(em);
        Iterator<Group> iterator = groups.iterator();

        while (iterator.hasNext()) {
            Group group = iterator.next();
            System.out.println(group.toString());
        }
    }
}
