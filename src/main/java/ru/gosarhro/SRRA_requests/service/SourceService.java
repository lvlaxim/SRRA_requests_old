package ru.gosarhro.SRRA_requests.service;

import org.springframework.stereotype.Service;
import ru.gosarhro.SRRA_requests.repository.requests.SourceRepository;
import ru.gosarhro.SRRA_requests.entity.requests.Source;

import java.util.List;

@Service
public class SourceService {

    private final SourceRepository repository;

    public SourceService(SourceRepository repository) {
        this.repository = repository;
    }

    public Source getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Source> getAll() {
        return repository.findAll();
    }
}
