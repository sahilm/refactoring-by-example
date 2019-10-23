package com.sahilm.legacy_csvs;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        final var inputFile = new File(args[0]);
        final var outputfile = new File(args[1]);

        final var fileType = FileType.detect(inputFile.getName());

        final var lines = fileType.reader().read(new FileReader(inputFile));
        final var report = SalaryReport.generate(lines);

        fileType.writer().write(report, new FileWriter(outputfile));
        System.out.printf("Report generated at: %s\n", outputfile);
    }
}
