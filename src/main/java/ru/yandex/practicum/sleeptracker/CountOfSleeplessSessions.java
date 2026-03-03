package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;

public class CountOfSleeplessSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {
    final String fnDescription = "Количество бессонных ночей";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        LocalTime midnight = LocalTime.of(0, 0);
        LocalTime endNight = LocalTime.of(6, 0);
        LocalTime afternoon = LocalTime.of(12, 0);

        // Про ChronoUnit вроде не было в лекциях, но не знаю как иначе учитывать дни между месяцами
        long allNights = ChronoUnit.DAYS.between(sleepingSessions.getFirst().getStartSession().toLocalDate(),
                sleepingSessions.getLast().getEndSession().toLocalDate());

        long firstNight =
                sleepingSessions.getFirst().getStartSession().toLocalTime().isBefore(afternoon)
                        ? 1L : 0L;

        long sleepNights = sleepingSessions
                .stream()
                .filter(session -> Period.between(session.getStartSession().toLocalDate(),
                        session.getEndSession().toLocalDate()).getDays() > 0
                        || (session.getStartSession().toLocalTime().isBefore(endNight)
                        && session.getStartSession().toLocalTime().isAfter(midnight)))
                .count();

        long result = allNights + firstNight - sleepNights;

        return new SleepAnalysisResult(fnDescription, result);
    }
}
