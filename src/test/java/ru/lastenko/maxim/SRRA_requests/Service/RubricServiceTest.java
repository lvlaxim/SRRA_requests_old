package ru.lastenko.maxim.SRRA_requests.Service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lastenko.maxim.SRRA_requests.entity.Rubric;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:db/populateDB.sql")
public class RubricServiceTest {

    public static final Rubric RUBRIC_1 = new Rubric(1, "Код1", "Рубрика1");
    public static final Rubric RUBRIC_2 = new Rubric(2, "Код2", "Рубрика2");

    @Autowired
    RubricService service;

    @Test
    public void getById() {
        Assert.assertEquals(RUBRIC_2, service.getById(2));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(List.of(RUBRIC_1, RUBRIC_2), service.getAll());
    }
}