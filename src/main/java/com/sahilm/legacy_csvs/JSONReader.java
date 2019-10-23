package com.sahilm.legacy_csvs;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Reader;
import java.util.List;

public class JSONReader implements LineReader {
    private final ObjectMapper mapper;

    public JSONReader(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<SalaryReport.Line> read(Reader reader) throws Exception {
        return mapper.readValue(reader, JSONReport.class).getLines();
    }
}

