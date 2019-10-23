package com.sahilm.legacy_csvs;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
    // TODO: Support JSON
    // TODO: Drop running totals in JSON. Only total needed
    public static void main(String[] args) throws Exception {
        final var inputFile = new File(args[0]);
        final var outputfile = new File(args[1]);

        final var lines = new CSVReader().read(new FileReader(inputFile));
        new CSVWriter().write(SalaryReport.generate(lines), new FileWriter(outputfile));
        System.out.printf("CSV generated at: %s\n", outputfile);
    }
}
