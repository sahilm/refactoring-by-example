package com.sahilm.legacy_csvs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Salary Report")
class AcceptanceTest {

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("it should generate a csv")
    void csvGeneration() throws Exception {
        final var inputFile = testFilePath("input.csv");
        final var outputFile = tempDir.resolve("output.csv").toString();
        Main.main(new String[]{inputFile, outputFile});
        final var expectedOutputFile = testFilePath("output.csv");
        assertThat(new File(outputFile)).hasSameContentAs(new File(expectedOutputFile));
    }

    private String testFilePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL file = classLoader.getResource(fileName);
        if (file == null) {
            throw new RuntimeException("cannot find test resource:" + fileName);
        }
        return file.getFile();
    }
}
