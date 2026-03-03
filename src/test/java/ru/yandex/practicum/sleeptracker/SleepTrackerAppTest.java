package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SleepTrackerAppTest {

    private List<SleepingSession> sessions;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    @BeforeEach
    void beforeEach() {
        sessions = new ArrayList<>();
    }

    @Test
    void shouldReturn2If2SleepSessions() {
        CountSessions countSessions = new CountSessions();
        SleepingSession session1 = new SleepingSession(LocalDateTime.parse("01.10.25 23:15", formatter),
                LocalDateTime.parse("01.10.25 23:15", formatter), SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(LocalDateTime.parse("01.10.25 23:15", formatter),
                LocalDateTime.parse("01.10.25 23:15", formatter), SleepQuality.GOOD);
        sessions.add(session1);
        sessions.add(session2);
        assertEquals(2, (long) countSessions.apply(sessions).getResult());
    }

    @Test
    void shouldReturn0IfNoSleepSessions() {
        CountSessions countSessions = new CountSessions();
        assertEquals(0, (long) countSessions.apply(sessions).getResult());
    }

    @Test
    void shouldReturnMinDuration10() {
        MinSessionDuration minSessionDuration = new MinSessionDuration();
        SleepingSession session1 = new SleepingSession(LocalDateTime.parse("01.10.25 23:15", formatter),
                LocalDateTime.parse("01.10.25 23:25", formatter), SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(LocalDateTime.parse("01.10.25 23:15", formatter),
                LocalDateTime.parse("01.10.25 23:35", formatter), SleepQuality.GOOD);
        sessions.add(session1);
        sessions.add(session2);
        assertEquals(10, (long) minSessionDuration.apply(sessions).getResult());
    }

    @Test
    void shouldReturnMin0IfNoSleepSessions() {
        MinSessionDuration minSessionDuration = new MinSessionDuration();
        assertEquals(0, (long) minSessionDuration.apply(sessions).getResult());
    }

    @Test
    void shouldReturnMaxDuration20() {
        MaxSessionDuration maxSessionDuration = new MaxSessionDuration();
        SleepingSession session1 = new SleepingSession(LocalDateTime.parse("01.10.25 23:15", formatter),
                LocalDateTime.parse("01.10.25 23:25", formatter), SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(LocalDateTime.parse("01.10.25 23:15", formatter),
                LocalDateTime.parse("01.10.25 23:35", formatter), SleepQuality.GOOD);
        sessions.add(session1);
        sessions.add(session2);

        assertEquals(20, (long) maxSessionDuration.apply(sessions).getResult());
    }

    @Test
    void shouldReturnMax0IfNoSleepSessions() {
        MaxSessionDuration maxSessionDuration = new MaxSessionDuration();
        assertEquals(0, (long) maxSessionDuration.apply(sessions).getResult());
    }

    @Test
    void shouldReturnAverageDuration15() {
        AverageSessionDuration averageSessionDuration = new AverageSessionDuration();
        SleepingSession session1 = new SleepingSession(LocalDateTime.parse("01.10.25 23:15", formatter),
                LocalDateTime.parse("01.10.25 23:25", formatter), SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(LocalDateTime.parse("01.10.25 23:15", formatter),
                LocalDateTime.parse("01.10.25 23:35", formatter), SleepQuality.GOOD);
        sessions.add(session1);
        sessions.add(session2);

        assertEquals(15, Integer.valueOf(averageSessionDuration.apply(sessions).getResult().toString()));
    }

    @Test
    void shouldReturnAverage0IfNoSleepSessions() {
        AverageSessionDuration averageSessionDuration = new AverageSessionDuration();
        assertEquals(0, Integer.valueOf(averageSessionDuration.apply(sessions).getResult().toString()));
    }

    @Test
    void shouldReturn2CountOfBadSleepSession() {
        CountOfBadSessions countOfBadSessions = new CountOfBadSessions();
        SleepingSession session1 = new SleepingSession(LocalDateTime.parse("01.10.25 23:15", formatter),
                LocalDateTime.parse("01.10.25 23:25", formatter), SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(LocalDateTime.parse("01.10.25 23:15", formatter),
                LocalDateTime.parse("01.10.25 23:35", formatter), SleepQuality.BAD);
        SleepingSession session3 = new SleepingSession(LocalDateTime.parse("02.10.25 23:15", formatter),
                LocalDateTime.parse("02.10.25 23:35", formatter), SleepQuality.BAD);

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);

        assertEquals(2, Integer.valueOf(countOfBadSessions.apply(sessions).getResult().toString()));
    }

    @Test
    void shouldReturn0IfNoBadSleepSession() {
        CountOfBadSessions countOfBadSessions = new CountOfBadSessions();
        SleepingSession session1 = new SleepingSession(LocalDateTime.parse("01.10.25 23:15", formatter),
                LocalDateTime.parse("01.10.25 23:25", formatter), SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(LocalDateTime.parse("01.10.25 23:15", formatter),
                LocalDateTime.parse("01.10.25 23:35", formatter), SleepQuality.GOOD);
        SleepingSession session3 = new SleepingSession(LocalDateTime.parse("02.10.25 23:15", formatter),
                LocalDateTime.parse("02.10.25 23:35", formatter), SleepQuality.NORMAL);

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);

        assertEquals(0, Integer.valueOf(countOfBadSessions.apply(sessions).getResult().toString()));
    }

    @Test
    void shouldReturn2SleeplessSession() {
        CountOfSleeplessSessions countOfSleeplessSessions = new CountOfSleeplessSessions();

        SleepingSession session1 = new SleepingSession(LocalDateTime.parse("01.10.25 23:15", formatter),
                LocalDateTime.parse("02.10.25 03:25", formatter), SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(LocalDateTime.parse("02.10.25 22:15", formatter),
                LocalDateTime.parse("02.10.25 23:35", formatter), SleepQuality.GOOD);
        SleepingSession session3 = new SleepingSession(LocalDateTime.parse("03.10.25 13:15", formatter),
                LocalDateTime.parse("03.10.25 14:35", formatter), SleepQuality.GOOD);
        SleepingSession session4 = new SleepingSession(LocalDateTime.parse("04.10.25 22:15", formatter),
                LocalDateTime.parse("04.10.25 23:00", formatter), SleepQuality.GOOD);

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);
        sessions.add(session4);

        assertEquals(2, Integer.valueOf(countOfSleeplessSessions.apply(sessions).getResult().toString()));
    }

    @Test
    void shouldReturn0SleeplessSessions() {
        CountOfSleeplessSessions countOfSleeplessSessions = new CountOfSleeplessSessions();

        SleepingSession session1 = new SleepingSession(LocalDateTime.parse("01.10.25 23:15", formatter),
                LocalDateTime.parse("02.10.25 03:25", formatter), SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(LocalDateTime.parse("02.10.25 22:15", formatter),
                LocalDateTime.parse("03.10.25 23:35", formatter), SleepQuality.GOOD);

        sessions.add(session1);
        sessions.add(session2);

        assertEquals(0, Integer.valueOf(countOfSleeplessSessions.apply(sessions).getResult().toString()));
    }

    @Test
    void shouldReturn2WithPreviousNightToCountSleeplessSessions() {
        CountOfSleeplessSessions countOfSleeplessSessions = new CountOfSleeplessSessions();

        SleepingSession session1 = new SleepingSession(LocalDateTime.parse("01.10.25 11:15", formatter),
                LocalDateTime.parse("01.10.25 19:25", formatter), SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(LocalDateTime.parse("02.10.25 12:15", formatter),
                LocalDateTime.parse("02.10.25 23:35", formatter), SleepQuality.GOOD);

        sessions.add(session1);
        sessions.add(session2);

        assertEquals(2, Integer.valueOf(countOfSleeplessSessions.apply(sessions).getResult().toString()));
    }

    @Test
    void shouldReturn2SleeplessNightsBetweenMonths() {
        CountOfSleeplessSessions countOfSleeplessSessions = new CountOfSleeplessSessions();

        SleepingSession session1 = new SleepingSession(LocalDateTime.parse("31.10.25 12:05", formatter),
                LocalDateTime.parse("31.10.25 23:59", formatter), SleepQuality.GOOD);
        SleepingSession session2 = new SleepingSession(LocalDateTime.parse("01.11.25 06:01", formatter),
                LocalDateTime.parse("01.11.25 11:59", formatter), SleepQuality.GOOD);
        SleepingSession session3 = new SleepingSession(LocalDateTime.parse("02.11.25 06:01", formatter),
                LocalDateTime.parse("02.11.25 12:00", formatter), SleepQuality.GOOD);

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);

        assertEquals(2, Integer.valueOf(countOfSleeplessSessions.apply(sessions).getResult().toString()));
    }

    @Test
    void shouldReturnOwlUserClassification() {
        UserClassification userClassification = new UserClassification();

        SleepingSession session1 = new SleepingSession(LocalDateTime.parse("01.10.25 23:01", formatter),
                LocalDateTime.parse("02.10.25 09:01", formatter), SleepQuality.GOOD);//сова
        SleepingSession session2 = new SleepingSession(LocalDateTime.parse("02.10.25 23:00", formatter),
                LocalDateTime.parse("03.10.25 09:00", formatter), SleepQuality.GOOD);//голубь
        SleepingSession session3 = new SleepingSession(LocalDateTime.parse("03.10.25 09:01", formatter),
                LocalDateTime.parse("03.10.25 22:59", formatter), SleepQuality.GOOD);//не учитывается
        SleepingSession session4 = new SleepingSession(LocalDateTime.parse("03.10.25 22:50", formatter),
                LocalDateTime.parse("04.10.25 09:01", formatter), SleepQuality.GOOD);//голубь
        SleepingSession session5 = new SleepingSession(LocalDateTime.parse("05.10.25 00:00", formatter),
                LocalDateTime.parse("05.10.25 10:00", formatter), SleepQuality.GOOD);//не учитывается
        SleepingSession session6 = new SleepingSession(LocalDateTime.parse("05.10.25 23:10", formatter),
                LocalDateTime.parse("06.10.25 10:00", formatter), SleepQuality.GOOD);//сова
        SleepingSession session7 = new SleepingSession(LocalDateTime.parse("06.10.25 23:10", formatter),
                LocalDateTime.parse("07.10.25 10:00", formatter), SleepQuality.GOOD);//сова

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);
        sessions.add(session4);
        sessions.add(session5);
        sessions.add(session6);
        sessions.add(session7);

        assertEquals("Сова", userClassification.apply(sessions).getResult());
    }

    @Test
    void shouldReturnPigeonUserClassification() {
        UserClassification userClassification = new UserClassification();

        SleepingSession session1 = new SleepingSession(LocalDateTime.parse("01.10.25 23:01", formatter),
                LocalDateTime.parse("02.10.25 09:01", formatter), SleepQuality.GOOD);//сова
        SleepingSession session2 = new SleepingSession(LocalDateTime.parse("02.10.25 23:00", formatter),
                LocalDateTime.parse("03.10.25 09:00", formatter), SleepQuality.GOOD);//голубь
        SleepingSession session3 = new SleepingSession(LocalDateTime.parse("03.10.25 09:01", formatter),
                LocalDateTime.parse("03.10.25 22:59", formatter), SleepQuality.GOOD);//не учитывается
        SleepingSession session4 = new SleepingSession(LocalDateTime.parse("03.10.25 22:50", formatter),
                LocalDateTime.parse("04.10.25 09:01", formatter), SleepQuality.GOOD);//голубь
        SleepingSession session5 = new SleepingSession(LocalDateTime.parse("05.10.25 21:59", formatter),
                LocalDateTime.parse("06.10.25 05:00", formatter), SleepQuality.GOOD);//жав

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);
        sessions.add(session4);
        sessions.add(session5);

        assertEquals("Голубь", userClassification.apply(sessions).getResult());
    }

    @Test
    void shouldReturnLarkUserClassification() {
        UserClassification userClassification = new UserClassification();

        SleepingSession session1 = new SleepingSession(LocalDateTime.parse("01.10.25 21:59", formatter),
                LocalDateTime.parse("02.10.25 06:59", formatter), SleepQuality.GOOD);//жав
        SleepingSession session2 = new SleepingSession(LocalDateTime.parse("02.10.25 21:59", formatter),
                LocalDateTime.parse("03.10.25 07:00", formatter), SleepQuality.GOOD);//голубь
        SleepingSession session3 = new SleepingSession(LocalDateTime.parse("03.10.25 22:00", formatter),
                LocalDateTime.parse("04.10.25 06:59", formatter), SleepQuality.GOOD);//голубь
        SleepingSession session4 = new SleepingSession(LocalDateTime.parse("04.10.25 21:00", formatter),
                LocalDateTime.parse("05.10.25 06:00", formatter), SleepQuality.GOOD);//жав
        SleepingSession session5 = new SleepingSession(LocalDateTime.parse("05.10.25 20:59", formatter),
                LocalDateTime.parse("06.10.25 05:00", formatter), SleepQuality.GOOD);//жав

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);
        sessions.add(session4);
        sessions.add(session5);

        assertEquals("Жаворонок", userClassification.apply(sessions).getResult());
    }
}