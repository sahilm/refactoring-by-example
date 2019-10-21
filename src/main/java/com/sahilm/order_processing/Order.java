package com.sahilm.order_processing;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Order {
    private final List<Line> lines;
    private final BigDecimal total;
    private final LocalDateTime processedAt;

    public Order(List<Line> lines) {
        this(lines, BigDecimal.ZERO, null);
    }

    public Order(List<Line> lines, BigDecimal total, LocalDateTime processedAt) {
        this.lines = lines;
        this.total = total;
        this.processedAt = processedAt;
    }

    // TODO: Record start and end times of order processing
    public Order process() {
        final var total = lines
            .stream()
            .map(line -> line.price)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Order(lines, total, LocalDateTime.now());
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Optional<LocalDateTime> getProcessedAt() {
        return Optional.of(processedAt);
    }

    public static class Line {
        private final String name;
        private final BigDecimal price;

        public Line(String name, BigDecimal price) {
            this.name = name;
            this.price = price;
        }
    }
}
