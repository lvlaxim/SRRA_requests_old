package ru.gosarhro.SRRA_requests.controller;

import org.springframework.stereotype.Controller;
import ru.gosarhro.SRRA_requests.entity.requests.Executor;
import ru.gosarhro.SRRA_requests.service.ExecutorService;

import java.util.List;

@Controller
public class ExecutorController {

    private final ExecutorService service;

    public ExecutorController(ExecutorService service) {
        this.service = service;
    }

    public Executor save(Executor executor) {
        return service.save(executor);
    }

    public Executor getById(int Id) {
        return service.getById(Id);
    }

    public List<Executor> getAll() {
        return service.getAll();
    }
}
