package ru.lastenko.maxim.SRRA_requests.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.lastenko.maxim.SRRA_requests.entity.Request;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class RequestSpecifications {

    public static Specification<Request> hasId(Integer id) {
        return (Specification<Request>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Request> hasOutNumber(Integer outNumber) {
        return (request, query, criteriaBuilder) -> criteriaBuilder.equal(request.get("outNumber"), outNumber);
    }

    public static Specification<Request> hasSmav(Integer smav) {
        return (request, query, criteriaBuilder) -> criteriaBuilder.equal(request.get("smav"), smav);
    }

    public static Specification<Request> hasTheme(String theme) {
        return (request, query, criteriaBuilder) -> criteriaBuilder.like(request.get("theme").get("name"), theme);
    }

    public static Specification<Request> answerContains(String answer) {
        return (request, query, criteriaBuilder) -> criteriaBuilder.like(request.get("shortAnswer"), "%" + answer + "%");
    }

    public static Specification<Request> hasExecutor(String executor) {
        return (request, query, criteriaBuilder) -> criteriaBuilder.like(request.get("executor").get("name"), executor);
    }

    public static Specification<Request> hasExecutDate(LocalDate executDate) {
        return (request, query, criteriaBuilder) -> criteriaBuilder.equal(request.get("endDate"), executDate);
    }
}

