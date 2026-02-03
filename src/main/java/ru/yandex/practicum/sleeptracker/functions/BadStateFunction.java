package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepState;
import ru.yandex.practicum.sleeptracker.SleepTrackerRecord;

import java.util.List;

public class BadStateFunction implements STAFunction {

    String title = "Количество ночей с плохим состоянием сна";

    @Override
    public SleepAnalysisResult records(List<SleepTrackerRecord> records) {
        long value = records.stream()
                .filter(r -> r.getSleepState() == SleepState.BAD)
                .count();
        return new SleepAnalysisResult(title, value);
    }
}
