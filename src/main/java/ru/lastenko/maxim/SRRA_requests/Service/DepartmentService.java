package ru.lastenko.maxim.SRRA_requests.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lastenko.maxim.SRRA_requests.entity.Department;
import ru.lastenko.maxim.SRRA_requests.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public Department getById(int id) {
        return repository.findById(id).orElse(null);
    }
}
