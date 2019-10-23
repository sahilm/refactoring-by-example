package com.sahilm.legacy_csvs;

import java.io.File;

public class Main {
    // TODO: Support JSON
    // TODO: Drop running totals in JSON. Only total needed
    public static void main(String[] args) throws Exception {
        final var inputFile = new File(args[0]);
        final var outputfile = new File(args[1]);
        SalaryReport.generate(inputFile, outputfile);
        System.out.printf("CSV generated at: %s\n", outputfile);
    }
}
