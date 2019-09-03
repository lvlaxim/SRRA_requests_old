package ru.lastenko.maxim.SRRA_requests.util;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static ru.lastenko.maxim.SRRA_requests.repository.RequestSpecifications.answerContains;
import static ru.lastenko.maxim.SRRA_requests.repository.RequestSpecifications.hasId;

public class RequestFilter {

    private Integer id;
    private Integer inNumber;
    private Integer themeId;
    private String answer;
    private Integer executorId;
    private LocalDate localDate;
    private Integer smav;

    public RequestFilter() {
    }

    public RequestFilter(Integer id, Integer inNumber, Integer themeId, String answer, Integer executorId, LocalDate localDate, Integer smav) {
        this.id = id;
        this.inNumber = inNumber;
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

    public Integer getInNumber() {
        return inNumber;
    }

    public void setInNumber(Integer inNumber) {
        this.inNumber = inNumber;
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
        if (answerIsEnabled()) {
            specifications.add(answerContains(answer));
        }
        int size = specifications.size();
        Specification specification;
        if (size > 0) {
//            specification = Specification.where(specifications.get(0));
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
                || answerIsEnabled()) {
            return true;
        }
        return false;
    }

    private Boolean answerIsEnabled(){
        return answer != null && !answer.isEmpty();
    }
}
