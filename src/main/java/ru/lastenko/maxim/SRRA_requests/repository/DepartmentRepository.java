package ru.lastenko.maxim.SRRA_requests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lastenko.maxim.SRRA_requests.entity.Department;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
