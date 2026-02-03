package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.ChronotypeFunction;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChronotypeFunctionTest {

    @Test
    void chronotypeOwlTest() {
        LocalDateTime date1 = LocalDateTime.of(2026, 1, 1, 23, 30);
        LocalDateTime date2 = LocalDateTime.of(2026, 1, 2, 11, 0);
        LocalDateTime date3 = LocalDateTime.of(2026, 1, 3, 23, 50);
        LocalDateTime date4 = LocalDateTime.of(2026, 1, 4, 12, 0);
        List<SleepTrackerRecord> records = new ArrayList<>();
        records.add(new SleepTrackerRecord(date1, date2, SleepState.BAD));
        records.add(new SleepTrackerRecord(date3, date4, SleepState.BAD));

        ChronotypeFunction chronotypeFunction = new ChronotypeFunction();

        assertEquals(Chronotype.OWL, chronotypeFunction.records(records).getChronotype(),
                "Пользователь должен быть совой, так как ложиться после 23 и просыпается после 9");
    }

    @Test
    void chronotypeLarkTest() {
        LocalDateTime date1 = LocalDateTime.of(2026, 1, 1, 21, 30);
        LocalDateTime date2 = LocalDateTime.of(2026, 1, 2, 6, 0);
        LocalDateTime date3 = LocalDateTime.of(2026, 1, 3, 20, 50);
        LocalDateTime date4 = LocalDateTime.of(2026, 1, 4, 5, 0);
        List<SleepTrackerRecord> records = new ArrayList<>();
        records.add(new SleepTrackerRecord(date1, date2, SleepState.BAD));
        records.add(new SleepTrackerRecord(date3, date4, SleepState.BAD));

        ChronotypeFunction chronotypeFunction = new ChronotypeFunction();

        assertEquals(Chronotype.LARK, chronotypeFunction.records(records).getChronotype(),
                "Пользователь должен быть совой, так как ложиться после 23 и просыпается после 9");
    }

    @Test
    void chronotypePigeonTest() {
        LocalDateTime date1 = LocalDateTime.of(2026, 1, 1, 23, 30);
        LocalDateTime date2 = LocalDateTime.of(2026, 1, 2, 10, 0);
        LocalDateTime date3 = LocalDateTime.of(2026, 1, 3, 20, 50);
        LocalDateTime date4 = LocalDateTime.of(2026, 1, 4, 5, 0);
        List<SleepTrackerRecord> records = new ArrayList<>();
        records.add(new SleepTrackerRecord(date1, date2, SleepState.BAD));
        records.add(new SleepTrackerRecord(date3, date4, SleepState.BAD));

        ChronotypeFunction chronotypeFunction = new ChronotypeFunction();

        assertEquals(Chronotype.PIGEON, chronotypeFunction.records(records).getChronotype(),
                "Пользователь должен быть совой, так как ложиться после 23 и просыпается после 9");
    }
}
