package com.sahilm.legacy_csvs;

import java.io.Writer;
import java.util.List;

public interface LineWriter {
    void write(List<SalaryReport.Line> lines, Writer writer) throws Exception;
}
