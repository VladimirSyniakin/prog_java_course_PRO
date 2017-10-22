package com.gmail.vsyniakin;

import javax.persistence.*;
import java.util.List;


public class Actions {

    public static <C> void addToDB(C entityDB, EntityManager em) {
        try {
            em.getTransaction().begin();
            em.persist(entityDB);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static void updateToDB(EntityManager em) {
        try {
            em.getTransaction().begin();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static void updateStudentToDB(Student student, EntityManager em) {
        try {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static Group searchGroupByName(String name, EntityManager em) {
        Query query = em.createNamedQuery("Group.searchByName", Group.class);
        query.setParameter("name", name);
        Group group = null;
        try {
            group = (Group) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return group;
    }

    public static Student searchStudentByName(String name, EntityManager em) {
        Query query = em.createNamedQuery("Student.searchByName", Student.class);
        query.setParameter("name", name);
        Student student;
        try {
            student = (Student) query.getSingleResult();
            return student;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Group> selectGroups(EntityManager em) {
        Query query = em.createNamedQuery("Group.showAllGroups", Group.class);
        List groups;
        try {
            groups = query.getResultList();
        } catch (Exception e) {
            return null;
        }
        return groups;
    }

    public static void deleteGroup(Group group, EntityManager em) {
        try {
            em.getTransaction().begin();
            em.remove(group);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
}
