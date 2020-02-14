package ru.lastenko.maxim.SRRA_requests.repository.source;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lastenko.maxim.SRRA_requests.entity.source.Source;

public interface SourceRepository extends JpaRepository<Source, Integer> {
}
