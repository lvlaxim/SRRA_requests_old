package ru.lastenko.maxim.SRRA_requests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.lastenko.maxim.SRRA_requests.Service.DepartmentService;
import ru.lastenko.maxim.SRRA_requests.entity.Department;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    public Department getById(int id) {
        return service.getById(id);
    }

}
