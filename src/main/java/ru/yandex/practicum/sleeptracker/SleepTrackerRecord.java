package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;

public class SleepTrackerRecord {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final SleepState sleepState;

    public SleepTrackerRecord(LocalDateTime start, LocalDateTime end, SleepState sleepState) {
        this.start = start;
        this.end = end;
        this.sleepState = sleepState;
    }

    public static long sleepSession(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).toMinutes();
    }

    public LocalDateTime getStart() {
        return start;
    }

    public SleepState getSleepState() {
        return sleepState;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
