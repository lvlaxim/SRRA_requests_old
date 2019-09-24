package ru.lastenko.maxim.SRRA_requests.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static ru.lastenko.maxim.SRRA_requests.TestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:db/populateDB.sql")
public class PaymentServiceTest {

    @Autowired
    private PaymentService service;

    @Test
    public void getById() {
        Assert.assertEquals(FREE_PAYMENT, service.getById(3));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(List.of(PAID_PAYMENT, PARTIALLY_PAID_PAYMENT, FREE_PAYMENT), service.getAll());
    }
}