package com.gmail.vsyniakin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("menu");
        ;
        EntityManager em = emf.createEntityManager();

        Dish dishOne = new Dish("dishOne", 15, 300, true);
        Dish dishTwo = new Dish("dishTwo", 35, 700, false);
        Dish dishThree = new Dish("dishThree", 25, 400, true);
        Dish dishFour = new Dish("dishFour", 30, 250, false);
        Dish dishFive = new Dish("dishFive", 20, 200, true);

        try (Scanner scanner = new Scanner(System.in)) {
            MethodsDB.addDishToDB(dishOne, em);
            MethodsDB.addDishToDB(dishTwo, em);
            MethodsDB.addDishToDB(dishThree, em);
            MethodsDB.addDishToDB(dishFour, em);
            MethodsDB.addDishToDB(dishFive, em);

            System.out.println("-------Блюда стоимость от-до:----------");
            List<Dish> list = MethodsDB.selectDishesByPrice(15, 30, em);
            iteratorDishesShow(list);

            System.out.println("-------Блюда только со скидкой:----------");
            list = MethodsDB.selectDishesWithDiscount(em);
            iteratorDishesShow(list);

            System.out.println("-------Набор блюд так, чтобы их суммарный вес был не более 1 КГ:----------");
            list = MethodsDB.selectAllDishes(em);
            iteratorDishesShow(searchDishOneKg(list));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void iteratorDishesShow(List<Dish> list) {
        Iterator<Dish> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static List<Dish> searchDishOneKg(List<Dish> list) {
        List<Dish> result = new ArrayList<>();
        int weight = 0;
        while (true) {
            int indexDish = (int) (Math.random() * list.size());
            Dish dish = list.get(indexDish);
            list.remove(indexDish);
            if ((weight + dish.getWeight()) < 1000) {
                weight = weight + dish.getWeight();
                result.add(dish);
            } else {
                return result;
            }
        }
    }
}
