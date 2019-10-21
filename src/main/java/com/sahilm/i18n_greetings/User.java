package com.sahilm.i18n_greetings;

public class User {

    private final String name;

    public User(String name) {
        this.name = name;
    }

    // TODO: Support greeting in English, Irish, Hindi and Chinese
    public String greet(Langs lang) {
        return String.format("%s, %s", lang.greeting(), name);
    }
}
