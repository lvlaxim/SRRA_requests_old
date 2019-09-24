package ru.lastenko.maxim.SRRA_requests.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.Rubric;
import ru.lastenko.maxim.SRRA_requests.repository.RubricRepository;

import java.util.List;

@Service
public class RubricService {

    @Autowired
    private RubricRepository repository;

    public Rubric getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Rubric> getAll() {
        return repository.findAll();
    }
}
