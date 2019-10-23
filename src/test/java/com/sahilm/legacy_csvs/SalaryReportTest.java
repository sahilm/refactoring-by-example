package com.sahilm.legacy_csvs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Salary Report")
class SalaryReportTest {
    @Test
    @DisplayName("it should generate report lines with running totals")
    void generate() {
        final var lines = List.of(
            new SalaryReport.Line("foo", new BigDecimal("1.2"), BigDecimal.ZERO),
            new SalaryReport.Line("bar", new BigDecimal("6.3"), BigDecimal.ZERO),
            new SalaryReport.Line("baz", new BigDecimal("11"), BigDecimal.ZERO)
        );

        final var generatedLines = SalaryReport.generate(lines);
        final var runningTotals = generatedLines.stream().map(SalaryReport.Line::getRunningTotal);
        final var expectedRunningTotals = List.of(
            new BigDecimal("1.2"),
            new BigDecimal("7.5"),
            new BigDecimal("18.5")
        );
        assertThat(runningTotals).isEqualTo(expectedRunningTotals);
    }
}
