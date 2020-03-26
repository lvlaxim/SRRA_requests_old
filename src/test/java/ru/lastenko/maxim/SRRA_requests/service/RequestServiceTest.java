package ru.lastenko.maxim.SRRA_requests.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.lastenko.maxim.SRRA_requests.entity.requests.Request;

import java.util.List;

import static ru.lastenko.maxim.SRRA_requests.TestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:db/populateDB.sql")
public class RequestServiceTest {


    @Autowired
    private RequestService service;

    @Test
    public void getById() {
        Assert.assertEquals(REQUEST4, service.getById(4));
    }

    @Test
    public void getAll() {
        List<Request> expectedRequests = List.of(
                REQUEST1,
                REQUEST2,
                REQUEST3,
                REQUEST4,
                REQUEST5,
                REQUEST6,
                REQUEST7,
                REQUEST8
        );
        Assert.assertEquals(expectedRequests, service.getAll());
    }

//    @Test
//    public void getAllOrderedByIdDesc() {
//        List<Request> expectedRequests = List.of(
//                REQUEST8,
//                REQUEST7,
//                REQUEST6,
//                REQUEST5,
//                REQUEST4,
//                REQUEST3,
//                REQUEST2,
//                REQUEST1
//        );
//
//        Assert.assertEquals(expectedRequests, service.getAllOrderedByIdDesc());
//    }
}