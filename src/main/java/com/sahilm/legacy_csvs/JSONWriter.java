package com.sahilm.legacy_csvs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;

public class JSONWriter implements LineWriter {
    private final ObjectMapper mapper;

    public JSONWriter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void write(List<SalaryReport.Line> lines, Writer writer) throws Exception {
        final var report = new JSONReport(lines, BigDecimal.ZERO);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(writer, report);
    }
}
