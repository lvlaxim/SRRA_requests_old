package ru.lastenko.maxim.SRRA_requests.service;

import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.Rubric;
import ru.lastenko.maxim.SRRA_requests.repository.RubricRepository;

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

    public List<Rubric> getAllOrderById() {return repository.findAllByOrderById();}

    public Rubric getById(int id) {
        return repository.findById(id).orElse(null);
    }
}
