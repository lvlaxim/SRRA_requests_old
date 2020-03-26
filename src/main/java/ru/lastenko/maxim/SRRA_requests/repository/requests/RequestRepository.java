package ru.lastenko.maxim.SRRA_requests.repository.requests;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.lastenko.maxim.SRRA_requests.entity.requests.Request;


public interface RequestRepository extends JpaRepository<Request, Integer>, JpaSpecificationExecutor<Request> {
    Page<Request> findAllByOrderByIdDesc(Pageable pageable);
}
