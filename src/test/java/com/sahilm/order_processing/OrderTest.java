package com.sahilm.order_processing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Orders")
class OrderTest {

    @Test
    @DisplayName("it should process an order")
    void process() {
        final var orderLines = List.of(
            new Order.Line("Soap", new BigDecimal("1.2")),
            new Order.Line("Tooth Brush", new BigDecimal("0.9")));

        final var tz = ZoneId.systemDefault();
        final var startTime = LocalDateTime.now().atZone(tz).toInstant();
        final var endTime = startTime.plusSeconds(1);
        final var times = List.of(startTime, endTime);

        final var order = new Order(orderLines, BigDecimal.ZERO, new FakeClock(times, tz));

        final var processedOrder = order.process();

        assertThat(processedOrder.getStartedProcessingAt())
            .isPresent()
            .hasValueSatisfying(pa -> assertThat(pa).isEqualTo(startTime.atZone(tz)));

        assertThat(processedOrder.getProcessedAt())
            .isPresent()
            .hasValueSatisfying(pa -> assertThat(pa).isEqualTo(endTime.atZone(tz)));

        assertThat(processedOrder.getTotal()).isEqualTo(new BigDecimal("2.1"));
    }
}
