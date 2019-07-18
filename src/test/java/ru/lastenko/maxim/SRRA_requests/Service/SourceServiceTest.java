package ru.lastenko.maxim.SRRA_requests.Service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lastenko.maxim.SRRA_requests.entity.Source;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:db/populateDB.sql")
public class SourceServiceTest {

    public static final Source SOURCE_1 = new Source(1, "Источник1");
    public static final Source SOURCE_2 = new Source(2, "Источник2");

    @Autowired
    private SourceService service;

    @Test
    public void getById() {
        Assert.assertEquals(SOURCE_2, service.getById(2));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(List.of(SOURCE_1, SOURCE_2), service.getAll());
    }
}