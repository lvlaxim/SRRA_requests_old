package ru.gosarhro.SRRA_requests.util;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import ru.gosarhro.SRRA_requests.repository.requests.RequestSpecifications;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Data
public class RequestFilter {
    private Integer id;
    private Integer outNumber;
    private Integer smav;
    private String subject;
    private String answer;
    private String executor;
    private LocalDate executeDateFrom;
    private LocalDate executeDateTo;
    private String inNumFromOrg;
    private Boolean caseInsensitive;
    private String initiator;
    private String shipment;

    public RequestFilter() {
    }

    public RequestFilter(Integer id, Integer outNumber, Integer smav, String subject, String answer, String executor, String executeDateFrom, String executeDateTo, String inNumFromOrg, Boolean caseInsensitive, String initiator, String shipment) {
        this.id = id;
        this.outNumber = outNumber;
        this.smav = smav;
        this.subject = subject;
        this.answer = answer;
        this.executor = executor;
        this.executeDateFrom = isEnabled(executeDateFrom) ? LocalDate.parse(executeDateFrom) : null;
        this.executeDateTo = isEnabled(executeDateTo) ? LocalDate.parse(executeDateTo) : null;
        this.inNumFromOrg = inNumFromOrg;
        this.caseInsensitive = caseInsensitive != null ? caseInsensitive : false;
        this.initiator = initiator;
        this.shipment = shipment;
    }

    public Specification getSpecification() {
        List<Specification> specifications = new LinkedList<>();
        if (id != null) {
            specifications.add(RequestSpecifications.hasId(id));
        }
        if (outNumber != null) {
            specifications.add(RequestSpecifications.hasOutNumber(outNumber));
        }
        if (smav != null) {
            specifications.add(RequestSpecifications.hasSmav(smav));
        }
        if (isEnabled(subject)) {
            String[] words = subject.split("\\+");
            for (String word : words) {
                if (caseInsensitive) {
                    specifications.add(RequestSpecifications.subjectContainsCaseInsensitive(subject));
                } else {
                    specifications.add(RequestSpecifications.subjectContains(subject));
                }
            }
        }
        if (isEnabled(answer)) {
            String[] words = answer.split("\\+");
            for (String word : words) {
                if (caseInsensitive) {
                    specifications.add(RequestSpecifications.answerContainsCaseInsensitive(word));
                } else {
                    specifications.add(RequestSpecifications.answerContains(word));
                }
            }
        }
        if (isEnabled(executor)) {
            specifications.add(RequestSpecifications.hasExecutor(executor));
        }

        if (executeDateFrom != null) {
            specifications.add(RequestSpecifications.endDateGreater(executeDateFrom));
        }

        if (executeDateTo != null) {
            specifications.add(RequestSpecifications.endDateLess(executeDateTo));
        }

        if (isEnabled(inNumFromOrg)) {
            specifications.add(RequestSpecifications.inNumFromOrgContains(inNumFromOrg));
        }

        int size = specifications.size();
        Specification specification;
        if (size > 0) {
            specification = specifications.get(0);
            if (size > 1) {
                for (int i = 1; i < size; i++) {
                    specification = specification.and(specifications.get(i));
                }
            }
            return specification;
        }

        return null;
    }

    public Boolean isEnabled() {
        return id != null
                || outNumber != null
                || smav != null
                || isEnabled(subject)
                || isEnabled(answer)
                || isEnabled(executor)
                || executeDateFrom != null
                || executeDateTo != null
                || isEnabled(inNumFromOrg)
                || initiator != null;
    }

    private Boolean isEnabled(String s) {
        return s != null && !s.isEmpty();
    }
}
