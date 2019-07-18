package ru.lastenko.maxim.SRRA_requests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lastenko.maxim.SRRA_requests.entity.WorkType;

public interface WorkTypeRepository extends JpaRepository<WorkType, Integer> {
}
