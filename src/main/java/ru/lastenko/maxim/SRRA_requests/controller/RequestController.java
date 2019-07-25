package ru.lastenko.maxim.SRRA_requests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lastenko.maxim.SRRA_requests.Service.RequestService;
import ru.lastenko.maxim.SRRA_requests.entity.Request;

import java.util.List;

@Controller
public class RequestController {

    @Autowired
    private RequestService service;

    @GetMapping("/")
    public String list(Model model) {
        List<Request> requests = service.getAllOrderedByIdDesc();
        model.addAttribute("requests", requests);
        return "index";
    }
}
