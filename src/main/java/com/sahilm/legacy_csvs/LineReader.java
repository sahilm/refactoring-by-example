package com.sahilm.legacy_csvs;

import java.io.Reader;
import java.util.List;

public interface LineReader {
    List<SalaryReport.Line> read(Reader reader) throws Exception;
}
