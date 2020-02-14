package ru.lastenko.maxim.SRRA_requests.repository.request;

import org.springframework.data.jpa.domain.Specification;
import ru.lastenko.maxim.SRRA_requests.entity.request.Request;

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

    public static Specification<Request> subjectContainsCaseInsensitive(String subject) {
        return (request, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(request.get("subject")), ("%" + subject + "%").toLowerCase());
    }

    public static Specification<Request> subjectContains(String subject) {
        return (request, query, criteriaBuilder) -> criteriaBuilder.like(request.get("subject"), "%" + subject + "%");
    }

    public static Specification<Request> answerContainsCaseInsensitive(String answer) {
        return (request, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(request.get("shortAnswer")), ("%" + answer + "%").toLowerCase());
    }

    public static Specification<Request> answerContains(String answer) {
        return (request, query, criteriaBuilder) -> criteriaBuilder.like(request.get("shortAnswer"), "%" + answer + "%");
    }

    public static Specification<Request> hasExecutor(String executor) {
        return (request, query, criteriaBuilder) -> criteriaBuilder.like(request.get("executor").get("name"), executor);
    }

    public static Specification<Request> endDateGreater(LocalDate endDateFrom) {
        return (request, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(request.get("endDate"), endDateFrom);
    }

    public static Specification<Request> endDateLess(LocalDate endDateTo) {
        return (request, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(request.get("endDate"), endDateTo);
    }

    public static Specification<Request> inNumFromOrgContains(String inNumFromOrg) {
        return (request, query, criteriaBuilder) -> criteriaBuilder.like(request.get("inNumFromOrg"), "%" + inNumFromOrg + "%");
    }
}

