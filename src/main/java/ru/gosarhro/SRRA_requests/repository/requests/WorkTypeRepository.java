package ru.gosarhro.SRRA_requests.repository.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gosarhro.SRRA_requests.entity.requests.WorkType;

public interface WorkTypeRepository extends JpaRepository<WorkType, Integer> {
}
