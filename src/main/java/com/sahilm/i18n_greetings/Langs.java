package com.sahilm.i18n_greetings;

public enum Langs {
    ENGLISH {
        @Override
        public String greeting() {
            return "Hello";
        }
    },
    IRISH {
        @Override
        public String greeting() {
            return "Dia dhuit";
        }
    },
    HINDI {
        @Override
        public String greeting() {
            return "नमस्ते";
        }
    },
    MANDARIN {
        @Override
        public String greeting() {
            return "你好";
        }
    };

    public abstract String greeting();
}

