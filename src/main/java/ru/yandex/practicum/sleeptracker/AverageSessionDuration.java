package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class AverageSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {
    final String fnDescription = "Средняя продолжительность сна, мин.";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        double result = sleepingSessions
                .stream()
                .mapToLong(session -> session.getDurationSession().toMinutes())
                .average()
                .orElse(0);

        return new SleepAnalysisResult(fnDescription, String.format("%.0f", result));
    }
}
