package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.Chronotype;

public class SleepAnalysisResult {
    private final String title;
    private long value;
    private Chronotype chronotype;

    public SleepAnalysisResult(String title, long value) {
        this.value = value;
        this.title = title;
    }

    public SleepAnalysisResult(String title, Chronotype chronotype) {
        this.title = title;
        this.chronotype = chronotype;
    }

    @Override
    public String toString() {
        if (value == 0) {
            return title + ": " + chronotype;
        }
        return title + ": " + value;
    }

    public String getTitle() {
        return title;
    }

    public long getValue() {
        return value;
    }

    public Chronotype getChronotype() {
        return chronotype;
    }
}
