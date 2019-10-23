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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    // TODO: Support JSON
    // TODO: Drop running totals in JSON. Only total needed
    public static void main(String[] args) throws Exception {
        final var inputFile = new File(args[0]);
        final var outputfile = new File(args[1]);

        final var lines = readCSV(inputFile);
        writeCSV(outputfile, SalaryReport.generate(lines));
        System.out.printf("CSV generated at: %s\n", outputfile);
    }

    static void writeCSV(File outputFile, List<SalaryReport.Line> lines) throws IOException {
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
            for (SalaryReport.Line line : lines) {
                writer.write(line, header, cellProcessors);
            }
        }
    }

    static List<SalaryReport.Line> readCSV(File inputFile) throws IOException {
        final List<SalaryReport.Line> lines = new ArrayList<>();
        try (final var reader = new CsvBeanReader(new FileReader(inputFile), CsvPreference.STANDARD_PREFERENCE)) {
            final var cellProcessors = new CellProcessor[]{
                new NotNull(),
                new ParseBigDecimal(),
            };
            final var header = reader.getHeader(true);
            SalaryReport.Line line;
            while ((line = reader.read(SalaryReport.Line.class, header, cellProcessors)) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
