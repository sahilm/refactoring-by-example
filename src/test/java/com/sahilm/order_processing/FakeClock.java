package com.sahilm.order_processing;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class FakeClock extends Clock {
    private final List<Instant> times;
    private final ZoneId zone;

    public FakeClock(List<Instant> times, ZoneId zoneID) {
        this.times = new ArrayList<>(times);
        this.zone = zoneID;
    }

    @Override
    public ZoneId getZone() {
        return zone;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return new FakeClock(times, zone);
    }

    @Override
    public Instant instant() {
        return times.remove(0);
    }
}
