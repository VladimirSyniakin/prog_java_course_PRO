package com.gmail.vsyniakin.query;

import com.gmail.vsyniakin.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserQuery {

    public static void addUserToDB(User user, EntityManager em) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static User selectUserByName(String name, EntityManager em) {
        Query query = em.createNamedQuery("User.searchByName", User.class);
        query.setParameter("name", name);
        User user;
        try {
            user = (User) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }


}
