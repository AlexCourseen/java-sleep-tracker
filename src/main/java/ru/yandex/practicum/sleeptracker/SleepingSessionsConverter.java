package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class SleepingSessionsConverter {
    public List<SleepingSession> convert(List<String> sessionsFromLog) {

        List<SleepingSession> sessions = sessionsFromLog
                .stream()
                .map(line -> line.split(";"))
                .map(line -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
                    LocalDateTime start = LocalDateTime.parse(line[0],formatter);
                    LocalDateTime end = LocalDateTime.parse(line[1],formatter);
                    SleepQuality sleepQuality = SleepQuality.valueOf(line[2]);
                    return new SleepingSession(start,end,sleepQuality);
                })
                .collect(Collectors.toList());

        return sessions;
    }
}
