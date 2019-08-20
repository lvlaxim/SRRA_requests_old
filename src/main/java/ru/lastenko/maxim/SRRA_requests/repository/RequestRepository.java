package ru.lastenko.maxim.SRRA_requests.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.lastenko.maxim.SRRA_requests.entity.Request;


import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer>, JpaSpecificationExecutor<Request> {

    Page<Request> findAllByOrderByIdDesc(Pageable pageable);

}
