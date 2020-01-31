package ru.lastenko.maxim.SRRA_requests.service;

import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.WorkType;
import ru.lastenko.maxim.SRRA_requests.repository.WorkTypeRepository;

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
