package ru.lastenko.maxim.SRRA_requests.util;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static ru.lastenko.maxim.SRRA_requests.repository.RequestSpecifications.*;

public class RequestFilter {

    private Integer id;
    private Integer outNumber;
    private Integer themeId;
    private String answer;
    private Integer executorId;
    private LocalDate localDate;
    private Integer smav;

    public RequestFilter() {
    }

    public RequestFilter(Integer id, Integer outNumber, Integer themeId, String answer, Integer executorId, LocalDate localDate, Integer smav) {
        this.id = id;
        this.outNumber = outNumber;
        this.themeId = themeId;
        this.answer = answer;
        this.executorId = executorId;
        this.localDate = localDate;
        this.smav = smav;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(Integer outNumber) {
        this.outNumber = outNumber;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getExecutorId() {
        return executorId;
    }

    public void setExecutorId(Integer executorId) {
        this.executorId = executorId;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Integer getSmav() {
        return smav;
    }

    public void setSmav(Integer smav) {
        this.smav = smav;
    }

    public Specification getSpecification() {
        List<Specification> specifications = new LinkedList<>();
        if (id != null) {
            specifications.add(hasId(id));
        }
        if (outNumber != null) {
            specifications.add(outNumberContains(outNumber));
        }
        if (isEnabled(answer)) {
            specifications.add(answerContains(answer));
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
        if (id != null
                || isEnabled(answer)
                || outNumber != null) {
            return true;
        }
        return false;
    }

    private Boolean isEnabled(String s) {
        return s != null && !s.isEmpty();
    }
}
