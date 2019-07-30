package ru.lastenko.maxim.SRRA_requests.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.lastenko.maxim.SRRA_requests.entity.Request;


import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    Page<Request> findAllByOrderByIdDesc(Pageable pageable);

}
