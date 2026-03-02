package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class SleepTrackerApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileLoader fileLoader = new FileLoader();
        List<String> sleepSessionsFromLog;
        SleepingSessionsConverter sleepingSessionsConverter = new SleepingSessionsConverter();
        List<SleepingSession> sessions;
        List<Function> functions = new ArrayList<>();

        //functions
        CountSessions countSessions = new CountSessions();
        MinSessionDuration minSessionDuration = new MinSessionDuration();
        MaxSessionDuration maxSessionDuration = new MaxSessionDuration();
        AverageSessionDuration averageSessionDuration = new AverageSessionDuration();
        CountOfBadSessions countOfBadSessions = new CountOfBadSessions();
        CountOfSleeplessSessions countOfSleeplessSessions = new CountOfSleeplessSessions();
        UserClassification userClassification = new UserClassification();

        functions.add(countSessions);
        functions.add(minSessionDuration);
        functions.add(maxSessionDuration);
        functions.add(averageSessionDuration);
        functions.add(countOfBadSessions);
        functions.add(countOfSleeplessSessions);
        functions.add(userClassification);

        // src/main/resources/sleep_log.txt
        System.out.println("Укажите путь к файлу лога сна:");
        String path = scanner.nextLine();
        try {
            sleepSessionsFromLog = fileLoader.load(path);
            sessions = sleepingSessionsConverter.convert(sleepSessionsFromLog);
            functions
                    .stream()
                    .map(fn -> fn.apply(sessions))
                    .peek(System.out::println)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}