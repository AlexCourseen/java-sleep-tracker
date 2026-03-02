package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class CountOfBadSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Object result;
        String fnDescription = "Количество сессий с плохим качество сна";

        result = sleepingSessions
                .stream()
                .filter(session -> session.sleepQuality.equals(SleepQuality.BAD))
                .count();

        return new SleepAnalysisResult(fnDescription, result);
    }
}
