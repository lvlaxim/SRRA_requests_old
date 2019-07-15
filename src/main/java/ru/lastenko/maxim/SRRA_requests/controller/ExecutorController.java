package ru.lastenko.maxim.SRRA_requests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.lastenko.maxim.SRRA_requests.Service.ExecutorService;
import ru.lastenko.maxim.SRRA_requests.entity.Executor;

import java.util.List;

@Controller
public class ExecutorController {

    @Autowired
    private ExecutorService service;

    public void setService(ExecutorService service) {
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

    public void deleteById(int Id) {
        service.deleteById(Id);
    }

}
