package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.MaxDurationFunction;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxDurationFunctionTest {

    @Test
    void maxDurationTest() {
        LocalDateTime date1 = LocalDateTime.of(2026, 1, 1, 22, 0);
        LocalDateTime date2 = LocalDateTime.of(2026, 1, 2, 2, 0);
        LocalDateTime date3 = LocalDateTime.of(2026, 1, 3, 22, 0);
        LocalDateTime date4 = LocalDateTime.of(2026, 1, 4, 3, 0);
        List<SleepTrackerRecord> records = new ArrayList<>();
        records.add(new SleepTrackerRecord(date1, date2, SleepState.BAD));
        records.add(new SleepTrackerRecord(date3, date4, SleepState.BAD));

        MaxDurationFunction function = new MaxDurationFunction();

        SleepAnalysisResult result = function.records(records);

        assertEquals(300, result.getValue(), "Должно возвращать наибольший промежуток сна");
    }

    @Test
    void zeroDurationTest() {
        MaxDurationFunction function = new MaxDurationFunction();
        assertEquals(0, function.records(List.of()).getValue(),
                "Должно возвращать промежуток размером 0");
    }
}
