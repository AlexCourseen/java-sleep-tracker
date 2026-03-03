package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;

public class SleepingSession {
    final private LocalDateTime startSession;
    final private LocalDateTime endSession;
    final private Duration durationSession;
    final private SleepQuality sleepQuality;

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
    public LocalDateTime getStartSession() {
        return startSession;
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }

    public LocalDateTime getEndSession() {
        return endSession;
    }

    public Duration getDurationSession() {
        return durationSession;
    }
}
