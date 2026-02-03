package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.SleeplessNightsCountFunction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SleeplessNightsCountFunctionTest {

    @Test
    void sleeplessNightsCountTest() {
        LocalDateTime date1 = LocalDateTime.of(2026, 1, 1, 22, 0);
        LocalDateTime date2 = LocalDateTime.of(2026, 1, 1, 23, 0);
        LocalDateTime date3 = LocalDateTime.of(2026, 1, 3, 6, 0);
        LocalDateTime date4 = LocalDateTime.of(2026, 1, 3, 10, 0);
        List<SleepTrackerRecord> records = new ArrayList<>();
        records.add(new SleepTrackerRecord(date1, date2, SleepState.BAD));
        records.add(new SleepTrackerRecord(date3, date4, SleepState.BAD));

        SleeplessNightsCountFunction sleeplessNights = new SleeplessNightsCountFunction();

        assertEquals(2, sleeplessNights.records(records).getValue(),
                "Дни бессонные, потому что не пересекают промежуток сна с 0:00-6:00");
    }

    @Test
    void nightWithSleepTest() {
        LocalDateTime date1 = LocalDateTime.of(2026, 1, 1, 13, 0);
        LocalDateTime date2 = LocalDateTime.of(2026, 1, 2, 23, 0);
        LocalDateTime date3 = LocalDateTime.of(2026, 1, 3, 2, 0);
        LocalDateTime date4 = LocalDateTime.of(2026, 1, 3, 6, 0);
        List<SleepTrackerRecord> records = new ArrayList<>();
        records.add(new SleepTrackerRecord(date1, date2, SleepState.BAD));
        records.add(new SleepTrackerRecord(date3, date4, SleepState.BAD));

        SleeplessNightsCountFunction sleeplessNights = new SleeplessNightsCountFunction();

        assertEquals(0, sleeplessNights.records(records).getValue(),
                "Дни не бессонные, потому что пересекают промежуток сна с 0:00-6:00");
    }

    @Test
    void nextDaySleepTeat() {
        LocalDateTime date1 = LocalDateTime.of(2026, 1, 1, 13, 0);
        LocalDateTime date2 = LocalDateTime.of(2026, 1, 1, 14, 0);
        List<SleepTrackerRecord> records = new ArrayList<>();
        records.add(new SleepTrackerRecord(date1, date2, SleepState.BAD));

        SleeplessNightsCountFunction sleeplessNights = new SleeplessNightsCountFunction();

        assertEquals(0, sleeplessNights.records(records).getValue(),
                "День должен считаться не бессонным, так как уже наступил следующий день");
    }

    @Test
    void previousDaySleepTeat() {
        LocalDateTime date1 = LocalDateTime.of(2026, 1, 1, 11, 0);
        LocalDateTime date2 = LocalDateTime.of(2026, 1, 1, 12, 0);
        List<SleepTrackerRecord> records = new ArrayList<>();
        records.add(new SleepTrackerRecord(date1, date2, SleepState.BAD));

        SleeplessNightsCountFunction sleeplessNights = new SleeplessNightsCountFunction();

        assertEquals(1, sleeplessNights.records(records).getValue(),
                "День должен считаться бессонным, так как следующий день ещё не наступил");
    }
}
