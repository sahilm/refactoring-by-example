package com.sahilm.legacy_csvs;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.Writer;
import java.util.List;

public class CSVWriter implements LineWriter {
    @Override
    public void write(List<SalaryReport.Line> lines, Writer writer) throws Exception {
        try (final var beanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE)) {
            final var cellProcessors = new CellProcessor[]{
                new NotNull(),
                new NotNull(),
                new NotNull(),
            };

            final var header = new String[]{
                "name",
                "salary",
                "runningTotal"
            };
            beanWriter.writeHeader(header);
            for (SalaryReport.Line line : lines) {
                beanWriter.write(line, header, cellProcessors);
            }
        }
    }
}
