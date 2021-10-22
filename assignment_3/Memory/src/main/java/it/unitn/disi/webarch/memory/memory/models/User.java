package it.unitn.disi.webarch.memory.memory.models;

import java.io.Serializable;

public class User implements Serializable {

    final private String name;

    public User() {
        this.name = null;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
