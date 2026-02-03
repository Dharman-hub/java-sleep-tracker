package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepTrackerRecord;

import java.util.List;

public class MinDurationFunction implements STAFunction {

    String title = "Продолжительность самого короткого сна в минутах";

    @Override
    public SleepAnalysisResult records(List<SleepTrackerRecord> records) {
        long value = records.stream()
                .mapToLong(r -> SleepTrackerRecord.sleepSession(r.getStart(), r.getEnd()))
                .min()
                .orElse(0);
        return new SleepAnalysisResult(title, value);
    }
}