package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class AverageSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Object result;
        String fnDescription = "Средняя продолжительность сна, мин.";

        result = sleepingSessions
                .stream()
                .mapToLong(session -> session.durationSession.toMinutes())
                .average()
                .orElse(0);

        return new SleepAnalysisResult(fnDescription, String.format("%.0f", result));
    }
}
