package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepTrackerRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ChronotypeFunction implements STAFunction {

    String title = "Определение хронотипа к которому относится пользователь";

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

        Map<Chronotype, Long> counts =
                startDate
                        .datesUntil(endDate.plusDays(1))
                        .map((LocalDate date) -> classifyNight(date, records))
                        .filter(Objects::nonNull)
                        .collect(groupingBy(
                                (Chronotype c) -> c,
                                counting()
                        ));

        long owlCount = counts.getOrDefault(Chronotype.OWL, 0L);
        long larkCount = counts.getOrDefault(Chronotype.LARK, 0L);
        long pigeonCount = counts.getOrDefault(Chronotype.PIGEON, 0L);

        if ((owlCount == larkCount && owlCount >= pigeonCount)
                || (owlCount == pigeonCount && owlCount >= larkCount)
                || (larkCount == pigeonCount && larkCount >= owlCount)) {
            return new SleepAnalysisResult(title, Chronotype.PIGEON);
        }

        if (owlCount > larkCount && owlCount > pigeonCount) {
            return new SleepAnalysisResult(title, Chronotype.OWL);
        }

        if (larkCount > owlCount && larkCount > pigeonCount) {
            return new SleepAnalysisResult(title, Chronotype.LARK);
        }

        return new SleepAnalysisResult(title, Chronotype.PIGEON);
    }

    static Chronotype classifyNight(LocalDate date, List<SleepTrackerRecord> records) {
        LocalDateTime nightPeriodStart = date.atTime(0, 0);
        LocalDateTime nightPeriodEnd = date.atTime(6, 0);

        SleepTrackerRecord nightSession = records.stream()
                .filter((SleepTrackerRecord r) -> sleepPeriod(r.getStart(), r.getEnd(), nightPeriodStart
                        , nightPeriodEnd))
                .findFirst()
                .orElse(null);

        if (nightSession == null) {
            return null;
        }

        LocalTime fallAsleep = nightSession.getStart().toLocalTime();
        LocalTime wakeUp = nightSession.getEnd().toLocalTime();

        boolean owl = fallAsleep.isAfter(LocalTime.of(23, 0))
                && wakeUp.isAfter(LocalTime.of(9, 0));
        boolean lark = fallAsleep.isBefore(LocalTime.of(22, 0))
                && wakeUp.isBefore(LocalTime.of(7, 0));

        if (owl) {
            return Chronotype.OWL;
        }
        if (lark) {
            return Chronotype.LARK;
        }
        return Chronotype.PIGEON;
    }

    public static boolean sleepPeriod(LocalDateTime firstStart, LocalDateTime lastEnd, LocalDateTime nightPeriodStart
            , LocalDateTime nightPeriodEnd) {
        return firstStart.isBefore(nightPeriodEnd) && lastEnd.isAfter(nightPeriodStart);
    }
}
