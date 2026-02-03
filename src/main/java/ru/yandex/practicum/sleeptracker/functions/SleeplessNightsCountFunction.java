package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepTrackerRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class SleeplessNightsCountFunction implements STAFunction {

    String title = "Количество бессонных ночей";

    @Override
    public SleepAnalysisResult records(List<SleepTrackerRecord> records) {
        LocalDateTime firstStart = records.getFirst().getStart();
        LocalDateTime lastEnd = records.getLast().getEnd();
        LocalDate startDate;

        if (firstStart.toLocalTime().isBefore(LocalTime.of(12, 0))) {
            startDate = firstStart.toLocalDate();
        } else {
            startDate = firstStart.toLocalDate().plusDays(1);
        }

        LocalDate endDate = lastEnd.toLocalDate();
        long totalNights = startDate.datesUntil(endDate.plusDays(1)).count();

        long sleepNights = startDate
                .datesUntil(endDate.plusDays(1))
                .filter(d -> boundariesOfSleep(d, records))
                .count();

        return new SleepAnalysisResult(title, totalNights - sleepNights);
        // В ТЗ написано, что человек не снимает часы. Как он не спал 22 дня, я не знаю...
    }

    public boolean boundariesOfSleep(LocalDate date, List<SleepTrackerRecord> records) {
        LocalDateTime nightPeriodStart = date.atTime(0, 0);
        LocalDateTime nightPeriodEnd = date.atTime(6, 0);
        return records.stream()
                .anyMatch(r -> sleepPeriod(r.getStart(), r.getEnd(), nightPeriodStart
                        , nightPeriodEnd));
    }

    public boolean sleepPeriod(LocalDateTime firstStart, LocalDateTime lastEnd, LocalDateTime nightPeriodStart
            , LocalDateTime nightPeriodEnd) {
        return firstStart.isBefore(nightPeriodEnd) && lastEnd.isAfter(nightPeriodStart);
    }
}
