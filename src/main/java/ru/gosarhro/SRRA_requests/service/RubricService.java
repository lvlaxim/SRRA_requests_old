package ru.gosarhro.SRRA_requests.service;

import org.springframework.stereotype.Service;
import ru.gosarhro.SRRA_requests.repository.requests.RubricRepository;
import ru.gosarhro.SRRA_requests.entity.requests.Rubric;

import java.util.List;

@Service
public class RubricService {

    private final RubricRepository repository;

    public RubricService(RubricRepository repository) {
        this.repository = repository;
    }

    public List<Rubric> getAll() {
        return repository.findAll();
    }

    public List<Rubric> getAllOrderById() {
        return repository.findAllByOrderById();
    }

    public Rubric getById(int id) {
        return repository.findById(id).orElse(null);
    }
}
