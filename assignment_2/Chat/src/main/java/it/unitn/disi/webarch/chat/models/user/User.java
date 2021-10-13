package it.unitn.disi.webarch.chat.models.user;

import java.io.Serializable;

public class User implements Serializable {

    private final String name;
    private final String password;

    public User() {
        this.name = null;
        this.password = null;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }

}
