package com.sahilm.legacy_csvs;

import org.supercsv.cellprocessor.ParseBigDecimal;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SalaryReport {
    public static void generate(File inputFile, File outputFile) throws Exception {
        final List<Line> lines = new ArrayList<>();
        try (final var reader = new CsvBeanReader(new FileReader(inputFile), CsvPreference.STANDARD_PREFERENCE)) {
            final var cellProcessors = new CellProcessor[]{
                new NotNull(),
                new ParseBigDecimal(),
            };
            final var header = reader.getHeader(true);
            Line line;
            while ((line = reader.read(Line.class, header, cellProcessors)) != null) {
                lines.add(line);
            }
        }

        var runningTotal = BigDecimal.ZERO;
        for (Line line : lines) {
            runningTotal = runningTotal.add(line.salary);
            line.setRunningTotal(runningTotal);
        }

        try (final var writer = new CsvBeanWriter(new FileWriter(outputFile), CsvPreference.STANDARD_PREFERENCE)) {
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
            writer.writeHeader(header);
            for (Line line : lines) {
                writer.write(line, header, cellProcessors);
            }
        }
    }

    public static class Line {
        private String name;
        private BigDecimal salary;
        private BigDecimal runningTotal;

        public Line() {
        }

        public BigDecimal getRunningTotal() {
            return runningTotal;
        }

        public void setRunningTotal(BigDecimal runningTotal) {
            this.runningTotal = runningTotal;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getSalary() {
            return salary;
        }

        public void setSalary(BigDecimal salary) {
            this.salary = salary;
        }
    }
}
