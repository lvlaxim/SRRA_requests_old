package ru.lastenko.maxim.SRRA_requests;

import ru.lastenko.maxim.SRRA_requests.entity.*;

import java.time.LocalDate;

public class TestData {

    // Department test data
    public static final Department DEPARTMENT_A = new Department(1, "НачальникА", "ОтделА");
    public static final Department DEPARTMENT_B = new Department(2, "НачальникБ", "ОтделБ");

    // Executor test data
    public static final Executor EXECUTOR_A = new Executor(2, "ИсполнительА", true, "ДолжностьА", "111-11-11", "executorA@gosarhro.ru");
    public static final Executor EXECUTOR_B = new Executor(1, "ИсполнительБ", true, "ДолжностьБ", "222-22-22", "executorB@gosarhro.ru");
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
    public static final Request REQUEST2 = new Request(2, RUBRIC_2, THEME_2,
            "Вопрос2", "Коротко2", "Ответ2", SOURCE_2, false,
            false, false, false, EXECUTOR_A, LocalDate.parse("2019-07-02"), DEPARTMENT_B,
            EXECUTOR_A, LocalDate.parse("2019-07-02"), EXECUTOR_A, LocalDate.parse("2019-07-03"), 2, 2,
            "2", "2", LocalDate.parse("2019-07-02"), 2, PARTIALLY_PAID_PAYMENT);

}
