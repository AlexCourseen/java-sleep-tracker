package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileLoader {
    public List<String> load(String file) throws IOException {
        File f = new File(file);

        if (!f.exists()) {
            throw new FileNotFoundException("Файл отсутствует.");
        }

        ArrayList<String> sessionsFromLog = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file, UTF_8))) {
            String line;
            while (br.ready()) {
                line = br.readLine().trim();
                sessionsFromLog.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionsFromLog;
    }
}
