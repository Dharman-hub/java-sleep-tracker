package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.functions.TotalSessionsFunction;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TotalSessionsFunctionTest {

    @Test
    void totalSessionsTest() {
        LocalDateTime date1 = LocalDateTime.of(2026, 1, 1, 22, 0);
        LocalDateTime date2 = LocalDateTime.of(2026, 1, 2, 2, 0);
        List<SleepTrackerRecord> records = new ArrayList<>();
        records.add(new SleepTrackerRecord(date1, date2, SleepState.BAD));

        TotalSessionsFunction function = new TotalSessionsFunction();

        SleepAnalysisResult result = function.records(records);

        assertEquals(1, result.getValue(), "Должно возвращать 1 сессию сна");
    }

    @Test
    void emptySessionsTest() {
        List<SleepTrackerRecord> records = new ArrayList<>();
        TotalSessionsFunction function = new TotalSessionsFunction();

        SleepAnalysisResult result = function.records(records);

        assertEquals(0, result.getValue(), "Должно возвращать 0 сессию сна");
    }
}
