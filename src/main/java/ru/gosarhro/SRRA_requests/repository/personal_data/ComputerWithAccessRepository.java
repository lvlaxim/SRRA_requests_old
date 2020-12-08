package ru.gosarhro.SRRA_requests.repository.personal_data;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gosarhro.SRRA_requests.entity.personal_data.ComputerWithAccess;

public interface ComputerWithAccessRepository extends JpaRepository<ComputerWithAccess, Integer> {
}
