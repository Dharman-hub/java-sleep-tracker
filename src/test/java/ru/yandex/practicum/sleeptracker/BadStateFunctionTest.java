package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.BadStateFunction;
import ru.yandex.practicum.sleeptracker.functions.SleepAnalysisResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadStateFunctionTest {

    @Test
    void badStateTest() {
        LocalDateTime date1 = LocalDateTime.of(2026, 1, 1, 22, 0);
        LocalDateTime date2 = LocalDateTime.of(2026, 1, 2, 2, 0);
        LocalDateTime date3 = LocalDateTime.of(2026, 1, 3, 22, 0);
        LocalDateTime date4 = LocalDateTime.of(2026, 1, 4, 3, 0);
        List<SleepTrackerRecord> records = new ArrayList<>();
        records.add(new SleepTrackerRecord(date1, date2, SleepState.BAD));
        records.add(new SleepTrackerRecord(date3, date4, SleepState.BAD));

        BadStateFunction function = new BadStateFunction();

        SleepAnalysisResult result = function.records(records);

        assertEquals(2, result.getValue(),
                "Должно возвращать количество ночей с плохим состоянием сна");
    }

    @Test
    void zeroDurationTest() {
        BadStateFunction function = new BadStateFunction();
        assertEquals(0, function.records(List.of()).getValue(),
                "Должно возвращать количество ночей с плохим состоянием сна");
    }
}
