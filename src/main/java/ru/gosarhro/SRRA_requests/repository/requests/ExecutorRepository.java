package ru.gosarhro.SRRA_requests.repository.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gosarhro.SRRA_requests.entity.requests.Executor;

import java.util.List;

public interface ExecutorRepository extends JpaRepository<Executor, Integer> {
    List<Executor> findAllByOrderByNameAsc();
}
