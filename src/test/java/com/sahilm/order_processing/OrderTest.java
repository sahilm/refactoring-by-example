package com.sahilm.order_processing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
        final var order = new Order(orderLines);
        final var now = LocalDateTime.now();
        final var processedOrder = order.process();

        assertThat(processedOrder.getProcessedAt())
            .isPresent()
            .hasValueSatisfying(pa -> assertThat(pa).isAfter(now));
        assertThat(processedOrder.getTotal()).isEqualTo(new BigDecimal("2.1"));
    }
}
