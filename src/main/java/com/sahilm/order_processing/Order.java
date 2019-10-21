package com.sahilm.order_processing;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public class Order {
    private final List<Line> lines;
    private final BigDecimal total;
    private final Clock clock;

    private ZonedDateTime processedAt;
    private ZonedDateTime startedProcessingAt;

    public Order(List<Line> lines, BigDecimal total, Clock clock) {
        this.lines = lines;
        this.total = total;
        this.clock = clock;
    }

    public Order(List<Line> lines, BigDecimal total) {
        this(lines, total, Clock.systemDefaultZone());
    }

    public Order process() {
        final var startedProcessingAt = ZonedDateTime.now(clock);
        final var total = lines
            .stream()
            .map(line -> line.price)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        final var order = new Order(lines, total);
        order.processedAt = ZonedDateTime.now(clock);
        order.startedProcessingAt = startedProcessingAt;
        return order;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Optional<ZonedDateTime> getProcessedAt() {
        return Optional.ofNullable(processedAt);
    }

    public Optional<ZonedDateTime> getStartedProcessingAt() {
        return Optional.ofNullable(startedProcessingAt);
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
