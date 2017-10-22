package com.gmail.vsyniakin;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class MethodsDB {

    public static void addDishToDB(Dish dish, EntityManager em) {
        try {
            em.getTransaction().begin();
            em.persist(dish);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static List<Dish> selectAllDishes(EntityManager em) {
        Query query = em.createNamedQuery("SelectAll", Dish.class);
        List dishes;
        try {
            dishes = query.getResultList();
        } catch (Exception e) {
            return null;
        }
        return dishes;
    }

    public static List<Dish> selectDishesWithDiscount(EntityManager em) {
        Query query = em.createNamedQuery("SelectWithDiscount", Dish.class);
        List dishes;
        try {
            dishes = query.getResultList();
        } catch (Exception e) {
            return null;
        }
        return dishes;
    }

    public static List<Dish> selectDishesByPrice(int fromPrice, int toPrice, EntityManager em) {
        Query query = em.createNamedQuery("SelectPrice", Dish.class);
        query.setParameter("fromPrice", fromPrice);
        query.setParameter("toPrice", toPrice);
        List dishes;
        try {
            dishes = query.getResultList();
        } catch (Exception e) {
            return null;
        }
        return dishes;
    }
}
