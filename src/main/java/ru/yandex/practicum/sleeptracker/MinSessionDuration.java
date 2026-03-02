package ru.yandex.practicum.sleeptracker;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class MinSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Object result;
        String fnDescription = "Минимальная продолжительность сна, мин.";

        result = sleepingSessions
                .stream()
                .min(Comparator.comparing(session -> session.durationSession))
                .map(session -> session.durationSession.toMinutes())
                .orElse(0L);

        return new SleepAnalysisResult(fnDescription, result);
    }
}
