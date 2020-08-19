package ru.gosarhro.SRRA_requests.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.gosarhro.SRRA_requests.repository.requests.RequestRepository;
import ru.gosarhro.SRRA_requests.entity.requests.Request;
import ru.gosarhro.SRRA_requests.util.RequestFilter;

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
