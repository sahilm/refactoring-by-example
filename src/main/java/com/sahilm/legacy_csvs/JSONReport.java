package com.sahilm.legacy_csvs;

import java.math.BigDecimal;
import java.util.List;

public class JSONReport {
    private List<SalaryReport.Line> lines;

    public JSONReport() {
    }

    public JSONReport(List<SalaryReport.Line> lines, BigDecimal total) {
        this.lines = lines;
    }

    public List<SalaryReport.Line> getLines() {
        return lines;
    }

    public void setLines(List<SalaryReport.Line> lines) {
        this.lines = lines;
    }
}
