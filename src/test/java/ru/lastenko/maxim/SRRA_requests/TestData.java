package ru.lastenko.maxim.SRRA_requests;

import ru.lastenko.maxim.SRRA_requests.entity.*;

import java.time.LocalDate;

public class TestData {

    // Executor test data
    public static final Executor EXECUTOR_B = new Executor(1, "ИсполнительБ", true, "ДолжностьБ", "222-22-22", "executorB@gosarhro.ru");
    public static final Executor EXECUTOR_A = new Executor(2, "ИсполнительА", true, "ДолжностьА", "111-11-11", "executorA@gosarhro.ru");
    public static final Executor EXECUTOR_V = new Executor(3, "ИсполнительВ", false, "ДолжностьВ", "333-33-33", "executorV@gosarhro.ru");

    // Payment test data
    public static final Payment PAID_PAYMENT = new Payment(1, "Платный");
    public static final Payment PARTIALLY_PAID_PAYMENT = new Payment(2, "Частично платный");
    public static final Payment FREE_PAYMENT = new Payment(3, "Бесплатный");

    // Rubric test data
    public static final Rubric RUBRIC_1 = new Rubric(1, "Код1", "Рубрика1");
    public static final Rubric RUBRIC_2 = new Rubric(2, "Код2", "Рубрика2");

    // Source test data
    public static final Source SOURCE_1 = new Source(1, "Источник1");
    public static final Source SOURCE_2 = new Source(2, "Источник2");

    // Theme test data
    public static final Theme THEME_1 = new Theme(1,"Тема1");
    public static final Theme THEME_2 = new Theme(2,"Тема2");

    // Work type test data
    public static final WorkType DIG = new WorkType(1, "Копать", "Штык", "1000");
    public static final WorkType THROW = new WorkType(2, "Кидать", "Ящик", "999.99");

    // Request test data
    public static final Request REQUEST1 = new Request(1, RUBRIC_1, THEME_1,
            "Вопрос1", "Коротко1", "Ответ1", SOURCE_1, false,
            false, false, false, EXECUTOR_B, LocalDate.parse("2019-07-01"), null,
            EXECUTOR_B, LocalDate.parse("2019-07-01"), EXECUTOR_B, LocalDate.parse("2019-07-02"), 1, 1,
            "1", "1", LocalDate.parse("2019-07-01"), 1, PAID_PAYMENT);
    public static final Request REQUEST2 = new Request(2, RUBRIC_2, THEME_2,
            "Вопрос2", "Коротко2", "Ответ2", SOURCE_2, false,
            false, false, false, EXECUTOR_A, LocalDate.parse("2019-07-02"), null,
            EXECUTOR_A, LocalDate.parse("2019-07-02"), EXECUTOR_A, LocalDate.parse("2019-07-03"), 2, 2,
            "2", "2", LocalDate.parse("2019-07-02"), 2, PARTIALLY_PAID_PAYMENT);
    public static final Request REQUEST3 = new Request(3, RUBRIC_1, THEME_2,
            "Вопрос3", "Коротко3", "Ответ3", SOURCE_1, false,
            false, false, false, EXECUTOR_V, LocalDate.parse("2019-07-03"), null,
            EXECUTOR_V, LocalDate.parse("2019-07-03"), EXECUTOR_V, LocalDate.parse("2019-07-04"), 3, 3,
            "3", "3", LocalDate.parse("2019-07-03"), 3, FREE_PAYMENT);
    public static final Request REQUEST4 = new Request(4, RUBRIC_2, THEME_1,
            "Срочный4", "Срочный4", "Срочный4", SOURCE_2, true,
            false, false, false, EXECUTOR_B, LocalDate.parse("2019-07-03"), null,
            EXECUTOR_A, LocalDate.parse("2019-07-03"), EXECUTOR_V, LocalDate.parse("2019-07-05"), 4, 4,
            "4", "4", LocalDate.parse("2019-07-03"), 4, FREE_PAYMENT);
    public static final Request REQUEST5 = new Request(5, RUBRIC_1, THEME_1,
            "Вопрос1", "Коротко1", "Ответ1", SOURCE_1, false,
            false, false, false, EXECUTOR_B, LocalDate.parse("2019-07-01"), null,
            EXECUTOR_B, LocalDate.parse("2019-07-01"), EXECUTOR_B, LocalDate.parse("2019-07-02"), 1, 1,
            "1", "1", LocalDate.parse("2019-07-01"), 1, PAID_PAYMENT);
    public static final Request REQUEST6 = new Request(6, RUBRIC_2, THEME_2,
            "Вопрос2", "Коротко2", "Ответ2", SOURCE_2, false,
            false, false, false, EXECUTOR_A, LocalDate.parse("2019-07-02"), null,
            EXECUTOR_A, LocalDate.parse("2019-07-02"), EXECUTOR_A, LocalDate.parse("2019-07-03"), 2, 2,
            "2", "2", LocalDate.parse("2019-07-02"), 2, PARTIALLY_PAID_PAYMENT);
    public static final Request REQUEST7 = new Request(7, RUBRIC_1, THEME_2,
            "Вопрос3", "Коротко3", "Ответ3", SOURCE_1, false,
            false, false, false, EXECUTOR_V, LocalDate.parse("2019-07-03"), null,
            EXECUTOR_V, LocalDate.parse("2019-07-03"), EXECUTOR_V, LocalDate.parse("2019-07-04"), 3, 3,
            "3", "3", LocalDate.parse("2019-07-03"), 3, FREE_PAYMENT);
    public static final Request REQUEST8 = new Request(8, RUBRIC_2, THEME_1,
            "Срочный4", "Срочный4", "Срочный4", SOURCE_2, true,
            false, false, false, EXECUTOR_B, LocalDate.parse("2019-07-03"), null,
            EXECUTOR_A, LocalDate.parse("2019-07-03"), EXECUTOR_V, LocalDate.parse("2019-07-05"), 4, 4,
            "4", "4", LocalDate.parse("2019-07-03"), 4, FREE_PAYMENT);
}
