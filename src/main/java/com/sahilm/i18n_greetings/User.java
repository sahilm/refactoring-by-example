package com.sahilm.i18n_greetings;

public class User {

    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String greet(Langs lang) {
        return String.format("%s, %s", lang.greeting(), name);
    }
}
