package ru.lastenko.maxim.SRRA_requests.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.request.Request;
import ru.lastenko.maxim.SRRA_requests.repository.request.RequestRepository;
import ru.lastenko.maxim.SRRA_requests.util.RequestFilter;

import java.util.List;


@Service
public class RequestService {
    private final RequestRepository repository;

    public RequestService(RequestRepository repository) {
        this.repository = repository;
    }

    public Request getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Request> getAll() {
        return repository.findAll();
    }

    public Page<Request> getByFilter(RequestFilter filter, Pageable pageable) {
        return repository.findAll(filter.getSpecification(), pageable);
    }

    public Request save(Request request) {
        return repository.save(request);
    }
}
