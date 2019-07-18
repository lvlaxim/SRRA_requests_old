package ru.lastenko.maxim.SRRA_requests.Service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lastenko.maxim.SRRA_requests.entity.WorkType;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:db/populateDB.sql")
public class WorkTypeServiceTest {

    public static final WorkType DIG = new WorkType(1, "Копать", "Штык", "1000");
    public static final WorkType THROW = new WorkType(2, "Кидать", "Ящик", "999.99");

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