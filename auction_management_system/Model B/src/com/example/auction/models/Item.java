package com.example.auction.models;

public class Item {
    public Long id;
    public String name;
    public String description;

    public Item(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
