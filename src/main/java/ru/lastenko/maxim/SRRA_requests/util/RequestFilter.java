package ru.lastenko.maxim.SRRA_requests.util;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static ru.lastenko.maxim.SRRA_requests.repository.requests.RequestSpecifications.*;

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


    public RequestFilter() {
    }

    public RequestFilter(Integer id, Integer outNumber, Integer smav, String subject, String answer, String executor, String executeDateFrom, String executeDateTo, String inNumFromOrg, Boolean caseInsensitive) {
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
    }

    public Specification getSpecification() {
        List<Specification> specifications = new LinkedList<>();
        if (id != null) {
            specifications.add(hasId(id));
        }
        if (outNumber != null) {
            specifications.add(hasOutNumber(outNumber));
        }
        if (smav != null) {
            specifications.add(hasSmav(smav));
        }
        if (isEnabled(subject)) {
            String[] words = subject.split("\\+");
            for (String word : words) {
                if (caseInsensitive) {
                    specifications.add(subjectContainsCaseInsensitive(subject));
                } else {
                    specifications.add(subjectContains(subject));
                }
            }
        }
        if (isEnabled(answer)) {
            String[] words = answer.split("\\+");
            for (String word : words) {
                if (caseInsensitive) {
                    specifications.add(answerContainsCaseInsensitive(word));
                } else {
                    specifications.add(answerContains(word));
                }
            }
        }
        if (isEnabled(executor)) {
            specifications.add(hasExecutor(executor));
        }

        if (executeDateFrom != null) {
            specifications.add(endDateGreater(executeDateFrom));
        }

        if (executeDateTo != null) {
            specifications.add(endDateLess(executeDateTo));
        }

        if (isEnabled(inNumFromOrg)) {
            specifications.add(inNumFromOrgContains(inNumFromOrg));
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
                || isEnabled(inNumFromOrg);
    }

    private Boolean isEnabled(String s) {
        return s != null && !s.isEmpty();
    }
}
