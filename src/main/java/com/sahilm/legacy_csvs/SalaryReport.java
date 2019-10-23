package com.sahilm.legacy_csvs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SalaryReport {
    public static List<Line> generate(List<Line> lines) {
        final var retLines = new ArrayList<Line>(lines.size());
        var runningTotal = BigDecimal.ZERO;
        for (Line line : lines) {
            runningTotal = runningTotal.add(line.salary);
            retLines.add(new Line(line.name, line.salary, runningTotal));
        }
        return retLines;
    }

    public static class Line {
        private String name;
        private BigDecimal salary;
        private BigDecimal runningTotal;

        public Line(String name, BigDecimal salary, BigDecimal runningTotal) {
            this.name = name;
            this.salary = salary;
            this.runningTotal = runningTotal;
        }

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Line line = (Line) o;

            if (!name.equals(line.name)) return false;
            if (!salary.equals(line.salary)) return false;
            return runningTotal.equals(line.runningTotal);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + salary.hashCode();
            result = 31 * result + runningTotal.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Line.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("salary=" + salary)
                .add("runningTotal=" + runningTotal)
                .toString();
        }
    }
}
