package ru.lastenko.maxim.SRRA_requests.Service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lastenko.maxim.SRRA_requests.entity.Request;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:db/populateDB.sql")
public class RequestServiceTest {

    public static final Request REQUEST2 = new Request(2, RubricServiceTest.RUBRIC_2, ThemeServiceTest.THEME2, "Вопрос2", "Коротко2", "Ответ2", SourceServiceTest.SOURCE2, false, false, false, false, ExecutorServiceTest.EXECUTOR_A, LocalDate.parse("2019-07-02"), DepartmentServiceTest.DEPARTMENT_B, ExecutorServiceTest.EXECUTOR_A, LocalDate.parse("2019-07-02"), ExecutorServiceTest.EXECUTOR_A, LocalDate.parse("2019-07-03"), 2, 2, "2", "2", LocalDate.parse("2019-07-02"), 2, PaymentServiceTest.PARTIALLY_PAID_PAYMENT);

    @Autowired
    private RequestService service;

    @Test
    public void getById() {
        Assert.assertEquals(REQUEST2,service.getById(2));
    }
}