package com.gmail.vsyniakin;

import javax.persistence.*;

@Entity
@Table(name="dishes")
@NamedQueries({
        @NamedQuery(name = "SelectAll", query = "SELECT d FROM Dish d"),
        @NamedQuery(name = "SelectWithDiscount", query = "SELECT d FROM Dish d WHERE d.discount = true"),
        @NamedQuery(name = "SelectPrice", query = "SELECT d FROM Dish d WHERE d.price > :fromPrice AND d.price < :toPrice")
})
public class Dish {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String name;
    private int price;
    private int weight;
    private boolean discount;

    public Dish(String name, int price, int weight, boolean discount) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
    }

    public Dish() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;

        Dish dish = (Dish) o;

        return id == dish.id;
    }

    @Override
    public int hashCode() {
        return id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", discount=" + discount +
                '}';
    }
}
