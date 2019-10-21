package com.sahilm.i18n_greetings;

public class User {

    private final String name;

    public enum LANGS {
        ENGLISH, IRISH
    }

    public User(String name) {
        this.name = name;
    }

    // TODO: Support greeting in English and Irish
    public String greet(LANGS language) {
        String greeting = switch (language) {
            case ENGLISH -> "Hello";
            case IRISH -> "Dia dhuit";
        };
        return String.format("%s, %s", greeting, name);
    }
}
