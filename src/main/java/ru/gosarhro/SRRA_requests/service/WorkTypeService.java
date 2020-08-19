package ru.gosarhro.SRRA_requests.service;

import org.springframework.stereotype.Service;
import ru.gosarhro.SRRA_requests.repository.requests.WorkTypeRepository;
import ru.gosarhro.SRRA_requests.entity.requests.WorkType;


import java.util.List;

@Service
public class WorkTypeService {

    private final WorkTypeRepository repository;

    public WorkTypeService(WorkTypeRepository repository) {
        this.repository = repository;
    }

    public WorkType getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<WorkType> getAll() {
        return repository.findAll();
    }
}
