package ru.lastenko.maxim.SRRA_requests.Service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lastenko.maxim.SRRA_requests.entity.Theme;
import ru.lastenko.maxim.SRRA_requests.repository.ThemeRepository;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:db/populateDB.sql")
public class ThemeServiceTest {

    public static final Theme THEME1 = new Theme(1,"Тема1");
    public static final Theme THEME2 = new Theme(2,"Тема2");

    @Autowired
    private ThemeService service;

    @Test
    public void getById() {
        Assert.assertEquals(THEME2, service.getById(2));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(List.of(THEME1, THEME2), service.getAll());
    }
}