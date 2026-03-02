package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;

public class UserClassification implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Object result;
        String fnDescription = "Тип пользователя";
        LocalTime midnight = LocalTime.of(0, 0);
        LocalTime endNight = LocalTime.of(6, 0);

        long sleepNights = sleepingSessions
                .stream()
                .filter(session -> Period.between(session.startSession.toLocalDate(),
                        session.endSession.plusMinutes(1).toLocalDate()).getDays() > 0
                        || (session.startSession.toLocalTime().isBefore(endNight)
                        && session.startSession.toLocalTime().isAfter(midnight)))
                .count();

        long owlNights = sleepingSessions
                .stream()
                .filter(session -> session.startSession.toLocalTime()
                        .isAfter(LocalTime.of(23, 0))
                        && session.endSession.toLocalTime()
                        .isAfter(LocalTime.of(9, 0)))
                .count();

        long larkNights = sleepingSessions
                .stream()
                .filter(session -> session.startSession.toLocalTime()
                        .isBefore(LocalTime.of(22, 0))
                        && session.endSession.toLocalTime()
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
