package com.sahilm.legacy_csvs;

import org.supercsv.cellprocessor.ParseBigDecimal;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements LineReader {
    @Override
    public List<SalaryReport.Line> read(Reader reader) throws Exception {
        final List<SalaryReport.Line> lines = new ArrayList<>();
        try (final var beanReader = new CsvBeanReader(reader, CsvPreference.STANDARD_PREFERENCE)) {
            final var cellProcessors = new CellProcessor[]{
                new NotNull(),
                new ParseBigDecimal(),
            };
            final var header = beanReader.getHeader(true);
            SalaryReport.Line line;
            while ((line = beanReader.read(SalaryReport.Line.class, header, cellProcessors)) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
