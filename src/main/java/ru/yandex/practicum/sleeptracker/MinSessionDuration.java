package ru.yandex.practicum.sleeptracker;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class MinSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {
    final String fnDescription = "Минимальная продолжительность сна, мин.";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        long result = sleepingSessions
                .stream()
                .min(Comparator.comparing(SleepingSession::getDurationSession))
                .map(session -> session.getDurationSession().toMinutes())
                .orElse(0L);

        return new SleepAnalysisResult(fnDescription, result);
    }
}
