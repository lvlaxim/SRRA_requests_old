package ru.lastenko.maxim.SRRA_requests.service;

import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.work_type.WorkType;
import ru.lastenko.maxim.SRRA_requests.repository.work_type.WorkTypeRepository;


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
