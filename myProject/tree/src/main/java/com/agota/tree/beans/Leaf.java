package com.agota.tree.beans;

import java.util.Random;

public class Leaf {

    private Long id;
    private String name;

    public Leaf(String name) {
        this.id = new Random().nextLong();
        Math.abs(this.id);
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
