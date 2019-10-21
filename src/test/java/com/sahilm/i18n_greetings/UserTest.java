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
        @DisplayName("it should provide a greeting")
        void greet() {
            final var sahil = new User("Sahil");
            assertThat(sahil.greet(Langs.ENGLISH)).isEqualTo("Hello, Sahil");
            assertThat(sahil.greet(Langs.IRISH)).isEqualTo("Dia dhuit, Sahil");
            assertThat(sahil.greet(Langs.HINDI)).isEqualTo("नमस्ते, Sahil");
            assertThat(sahil.greet(Langs.MANDARIN)).isEqualTo("你好, Sahil");
        }
    }
}
