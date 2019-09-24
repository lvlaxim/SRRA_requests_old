package ru.lastenko.maxim.SRRA_requests.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.Request;
import ru.lastenko.maxim.SRRA_requests.repository.RequestRepository;
import ru.lastenko.maxim.SRRA_requests.util.RequestFilter;

import java.util.List;


@Service
public class RequestService {

    @Autowired
    private RequestRepository repository;

    public Request getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Request> getAll() {
        return repository.findAll();
    }

    public Page<Request> getByFilter(RequestFilter filter, Pageable pageable) {
        return repository.findAll(filter.getSpecification(), pageable);
    }

    public void save(Request request) {
        repository.save(request);
    }

}
