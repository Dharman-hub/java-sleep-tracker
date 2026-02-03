package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepTrackerRecord;

import java.util.List;

public class AvgDurationFunction implements STAFunction {

    String title = "Средняя продолжительность сна в минутах";

    @Override
    public SleepAnalysisResult records(List<SleepTrackerRecord> records) {
        long value = (long) records.stream()
                .mapToLong(r -> SleepTrackerRecord.sleepSession(r.getStart(), r.getEnd()))
                .average()
                .orElse(0);
        return new SleepAnalysisResult(title, value);
    }
}
