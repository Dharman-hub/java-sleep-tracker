package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class SleepTrackerApp {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    private static final List<STAFunction> appFunctions = List.of(new MaxDurationFunction(),
            new MinDurationFunction(),
            new BadStateFunction(),
            new AvgDurationFunction(),
            new SleeplessNightsCountFunction(),
            new TotalSessionsFunction(),
            new ChronotypeFunction());

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Укажите путь к файлу с логом сна в аргументах запуска");
            return;
        }

        List<SleepTrackerRecord> records =
                Files.readAllLines(Path.of(args[0])).stream()
                        .map(SleepTrackerApp::parseLine)   // Optional<SleepTrackerRecord>
                        .flatMap(java.util.Optional::stream)
                        .toList();

        appFunctions.stream()
                .map(f -> f.records(records))
                .forEach(System.out::println);

    }

    static Optional<SleepTrackerRecord> parseLine(String line) {
        try {
            String[] parts = line.split(";");
            LocalDateTime start = LocalDateTime.parse(parts[0].trim(), FMT);
            LocalDateTime end = LocalDateTime.parse(parts[1].trim(), FMT);
            SleepState sleepState = SleepState.valueOf(parts[2].trim());
            return Optional.of(new SleepTrackerRecord(start, end, sleepState));
        } catch (Exception e) {
            System.out.println("В файле присутствует некорректная строка");
            return Optional.empty();
        }

    }
}