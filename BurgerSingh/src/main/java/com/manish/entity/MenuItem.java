package com.manish.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_items")  
public class MenuItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Integer quantity;  
    
    @Column(nullable = false)
    private Double price; 


    public MenuItem() {super();}

    public MenuItem(int id, String name, Integer quantity, Double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
 

    // Getters and Setters
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {  
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {  
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItem [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
    }
}
