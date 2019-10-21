package com.sahilm.i18n_greetings;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Users")
class UserTest {

    @Nested
    @DisplayName("greetings")
    class Greetings {
        @Test
        @DisplayName("it should provide a greeting in English")
        void greetInEnglish() {
            final var sahil = new User("Sahil");
            assertThat(sahil.greet(User.LANGS.ENGLISH)).isEqualTo("Hello, Sahil");
        }

        @Test
        @DisplayName("it should provide a greeting in Irish")
        void greetInIrish() {
            final var sahil = new User("Sahil");
            assertThat(sahil.greet(User.LANGS.IRISH)).isEqualTo("Dia dhuit, Sahil");
        }
    }
}
