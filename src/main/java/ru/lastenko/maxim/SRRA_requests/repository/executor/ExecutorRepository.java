package ru.lastenko.maxim.SRRA_requests.repository.executor;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lastenko.maxim.SRRA_requests.entity.executor.Executor;

import java.util.List;

public interface ExecutorRepository extends JpaRepository<Executor, Integer> {
    List<Executor> findAllByOrderByNameAsc();
}
