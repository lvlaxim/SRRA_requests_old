package ru.lastenko.maxim.SRRA_requests.repository.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lastenko.maxim.SRRA_requests.entity.requests.Source;

public interface SourceRepository extends JpaRepository<Source, Integer> {
}
