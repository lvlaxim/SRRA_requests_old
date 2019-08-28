package ru.lastenko.maxim.SRRA_requests.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.lastenko.maxim.SRRA_requests.entity.Request;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RequestSpecifications {

    public static Specification<Request> hasId(Integer id) {
        return (Specification<Request>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Request> answerContains(String answer) {
        return (request, query,criteriaBuilder) -> criteriaBuilder.like(request.get("shortAnswer"), "%" + answer + "%");
    }

}

