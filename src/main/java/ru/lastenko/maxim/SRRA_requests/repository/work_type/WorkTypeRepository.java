package ru.lastenko.maxim.SRRA_requests.repository.work_type;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lastenko.maxim.SRRA_requests.entity.work_type.WorkType;

public interface WorkTypeRepository extends JpaRepository<WorkType, Integer> {
}
