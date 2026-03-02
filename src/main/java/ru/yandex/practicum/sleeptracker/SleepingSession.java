package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;

public class SleepingSession {
    final LocalDateTime startSession;
    final LocalDateTime endSession;
    final Duration durationSession;
    final SleepQuality sleepQuality;

    public SleepingSession(LocalDateTime startSession,LocalDateTime endSession,SleepQuality sleepQuality) {
        this.startSession = startSession;
        this.endSession = endSession;
        this.sleepQuality = sleepQuality;
        durationSession = Duration.between(startSession,endSession);
    }

    @Override
    public String toString() {
        return "SleepingSession{" +
                "startSession=" + startSession +
                ", endSession=" + endSession +
                ", durationSession=" + durationSession +
                ", sleepQuality=" + sleepQuality +
                '}';
    }
}
