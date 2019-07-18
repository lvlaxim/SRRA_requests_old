package ru.lastenko.maxim.SRRA_requests.Service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lastenko.maxim.SRRA_requests.entity.Payment;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:db/populateDB.sql")
public class PaymentServiceTest {

    public static final Payment PAID_PAYMENT = new Payment(1, "Платный");
    public static final Payment PARTIALLY_PAID_PAYMENT = new Payment(2, "Частично платный");
    public static final Payment FREE_PAYMENT = new Payment(3, "Бесплатный");

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