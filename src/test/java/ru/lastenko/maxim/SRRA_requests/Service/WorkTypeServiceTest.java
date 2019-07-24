package ru.lastenko.maxim.SRRA_requests.Service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lastenko.maxim.SRRA_requests.TestData;

import java.util.List;

import static ru.lastenko.maxim.SRRA_requests.TestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:db/populateDB.sql")
public class WorkTypeServiceTest {

    @Autowired
    private WorkTypeService service;

    @Test
    public void getById() {
        Assert.assertEquals(THROW, service.getById(2));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(List.of(DIG, THROW), service.getAll());
    }
}