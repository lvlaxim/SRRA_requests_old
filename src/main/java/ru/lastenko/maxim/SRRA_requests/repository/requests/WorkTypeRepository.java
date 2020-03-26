package ru.lastenko.maxim.SRRA_requests.repository.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lastenko.maxim.SRRA_requests.entity.requests.WorkType;

public interface WorkTypeRepository extends JpaRepository<WorkType, Integer> {
}
