package ru.lastenko.maxim.SRRA_requests.repository.rubric;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lastenko.maxim.SRRA_requests.entity.rubric.Rubric;

import java.util.List;

@Repository
public interface RubricRepository extends JpaRepository<Rubric, Integer> {
    List<Rubric> findAllByOrderById();
}
