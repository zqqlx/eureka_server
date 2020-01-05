package net.xdclass.eureka_server.domain.entity;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;

    private String name;


    private String price;

    private int count;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
