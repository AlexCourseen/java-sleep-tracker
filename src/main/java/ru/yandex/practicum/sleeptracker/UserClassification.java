package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;

public class UserClassification implements Function<List<SleepingSession>, SleepAnalysisResult> {
    final String fnDescription = "Тип пользователя";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        String result;
        LocalTime midnight = LocalTime.of(0, 0);
        LocalTime endNight = LocalTime.of(6, 0);

        long sleepNights = sleepingSessions
                .stream()
                .filter(session -> Period.between(session.getStartSession().toLocalDate(),
                        session.getEndSession().plusMinutes(1).toLocalDate()).getDays() > 0
                        || (session.getStartSession().toLocalTime().isBefore(endNight)
                        && session.getStartSession().toLocalTime().isAfter(midnight)))
                .count();

        long owlNights = sleepingSessions
                .stream()
                .filter(session -> session.getStartSession().toLocalTime()
                        .isAfter(LocalTime.of(23, 0))
                        && session.getEndSession().toLocalTime()
                        .isAfter(LocalTime.of(9, 0)))
                .count();

        long larkNights = sleepingSessions
                .stream()
                .filter(session -> session.getStartSession().toLocalTime()
                        .isBefore(LocalTime.of(22, 0))
                        && session.getEndSession().toLocalTime()
                        .isBefore(LocalTime.of(7, 0)))
                .count();

        long pigeonNights = sleepNights - larkNights - owlNights;

        if (owlNights > larkNights && owlNights > pigeonNights) {
            result = UserType.OWL.getName();
        } else if (larkNights > owlNights && larkNights > pigeonNights) {
            result = UserType.LARK.getName();
        } else {
            result = UserType.PIGEON.getName();
        }

        return new SleepAnalysisResult(fnDescription, result);
    }
}
