package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;

public class CountOfSleeplessSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Object result;
        String fnDescription = "Количество бессонных ночей";

        LocalTime midnight = LocalTime.of(0, 0);
        LocalTime endNight = LocalTime.of(6, 0);
        LocalTime afternoon = LocalTime.of(12, 0);

        // Про ChronoUnit вроде не было в лекциях, но не знаю как иначе учитывать дни между месяцами
        long allNights = ChronoUnit.DAYS.between(sleepingSessions.getFirst().startSession.toLocalDate(),
                sleepingSessions.getLast().endSession.toLocalDate());

        long firstNight =
                sleepingSessions.getFirst().startSession.toLocalTime().isBefore(afternoon)
                        ? 1L : 0L;

        long sleepNights = sleepingSessions
                .stream()
                .filter(session -> Period.between(session.startSession.toLocalDate(),
                        session.endSession.toLocalDate()).getDays() > 0
                        || (session.startSession.toLocalTime().isBefore(endNight)
                        && session.startSession.toLocalTime().isAfter(midnight)))
                .count();

        result = allNights + firstNight - sleepNights;

        return new SleepAnalysisResult(fnDescription, result);
    }
}
