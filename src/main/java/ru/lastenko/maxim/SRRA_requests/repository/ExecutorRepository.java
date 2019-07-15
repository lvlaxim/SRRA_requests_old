package ru.lastenko.maxim.SRRA_requests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lastenko.maxim.SRRA_requests.entity.Executor;

import java.util.List;

public interface ExecutorRepository extends JpaRepository<Executor, Integer> {

    List<Executor> findAllByOrderByExecutorAsc();

}
