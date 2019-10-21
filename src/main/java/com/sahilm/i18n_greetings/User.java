package com.sahilm.i18n_greetings;

public class User {

    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String greet() {
        return String.format("%s, %s", "Hello", name);
    }
}
