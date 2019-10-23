package com.sahilm.legacy_csvs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;

public enum FileType {
    CSV {
        @Override
        public LineReader reader() {
            return new CSVReader();
        }

        @Override
        public LineWriter writer() {
            return new CSVWriter();
        }
    },
    JSON {
        @Override
        public LineReader reader() {
            return new JSONReader(new ObjectMapper());
        }

        @Override
        public LineWriter writer() {
            return new JSONWriter(new ObjectMapper());
        }
    };

    public abstract LineReader reader();

    public abstract LineWriter writer();

    public static FileType detect(String fileName) {
        final var ext = Files.getFileExtension(fileName);
        return FileType.valueOf(ext.toUpperCase());
    }
}
