package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepTrackerRecord;

import java.util.List;

public class TotalSessionsFunction implements STAFunction {

    String title = "Общее количество сессий сна";

    @Override
    public SleepAnalysisResult records(List<SleepTrackerRecord> records) {
        return new SleepAnalysisResult(title, records.size());
    }
}
