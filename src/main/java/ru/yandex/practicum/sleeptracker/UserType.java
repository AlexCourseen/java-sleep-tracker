package ru.yandex.practicum.sleeptracker;

public enum UserType {
    OWL("Сова"),
    LARK("Жаворонок"),
    PIGEON("Голубь");

    final String name;

    UserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
