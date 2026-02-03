package ru.yandex.practicum.sleeptracker.functions;


import ru.yandex.practicum.sleeptracker.SleepTrackerRecord;

import java.util.List;

public interface STAFunction {
    SleepAnalysisResult records(List<SleepTrackerRecord> records);
}
