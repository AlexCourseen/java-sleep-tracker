package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class CountSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Object result;
        String fnDescription = "Количество сессий сна";

        result = sleepingSessions
                    .stream()
                    .count();

        return new SleepAnalysisResult(fnDescription, result);
    }
}
