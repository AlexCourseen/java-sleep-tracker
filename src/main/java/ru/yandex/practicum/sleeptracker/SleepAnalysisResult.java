package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {
    final String funcDescription;
    final Object result;

    public SleepAnalysisResult(String funcDescription, Object result) {
        this.funcDescription = funcDescription;
        this.result = result;
    }

    @Override
    public String toString() {
        return funcDescription + ": " + result;
    }

    public String getFuncDescription() {
        return funcDescription;
    }

    public Object getResult() {
        return result;
    }
}
