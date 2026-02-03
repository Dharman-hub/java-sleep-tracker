package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepTrackerRecord;

import java.util.List;

public class MaxDurationFunction implements STAFunction {

    String title = "Продолжительность самого длинного сна в минутах";

    @Override
    public SleepAnalysisResult records(List<SleepTrackerRecord> records) {
        long value = records.stream()
                .mapToLong(r -> SleepTrackerRecord.sleepSession(r.getStart(), r.getEnd()))
                .max()
                .orElse(0);
        return new SleepAnalysisResult(title, value);
    }
}
