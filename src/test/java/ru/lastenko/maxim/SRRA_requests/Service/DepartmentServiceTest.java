package ru.lastenko.maxim.SRRA_requests.Service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lastenko.maxim.SRRA_requests.entity.Department;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:db/populateDB.sql")
public class DepartmentServiceTest {

    public static final Department DEPARTMENT_B = new Department(2, "НачальникБ", "ОтделБ");

    @Autowired
    DepartmentService service;

    @Test
    public void getById() {
        Assert.assertEquals(DEPARTMENT_B, service.getById(2));
    }
}