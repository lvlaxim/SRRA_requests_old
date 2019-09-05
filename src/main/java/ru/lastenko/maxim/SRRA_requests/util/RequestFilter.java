package ru.lastenko.maxim.SRRA_requests.util;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static ru.lastenko.maxim.SRRA_requests.repository.RequestSpecifications.*;

public class RequestFilter {

    private Integer id;
    private Integer outNumber;
    private Integer smav;
    private String theme;
    private String answer;
    private String executor;
    private LocalDate executeDate;
    private Boolean caseInsensitive;


    public RequestFilter() {
    }

    public RequestFilter(Integer id, Integer outNumber, Integer smav, String theme, String answer, String executor, String executeDate, Boolean caseInsensitive) {
        this.id = id;
        this.outNumber = outNumber;
        this.smav = smav;
        this.theme = theme;
        this.answer = answer;
        this.executor = executor;
        this.executeDate = isEnabled(executeDate) ? LocalDate.parse(executeDate) : null;
        this.caseInsensitive = caseInsensitive!=null ? caseInsensitive : false;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public LocalDate getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(LocalDate executeDate) {
        this.executeDate = executeDate;
    }

    public Integer getSmav() {
        return smav;
    }

    public void setSmav(Integer smav) {
        this.smav = smav;
    }

    public Boolean getCaseInsensitive() {
        return caseInsensitive;
    }

    public void setCaseInsensitive(Boolean caseInsensitive) {
        this.caseInsensitive = caseInsensitive;
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
        if (isEnabled(theme)) {
            specifications.add(hasTheme(theme));
        }
        if (isEnabled(answer)) {
            List<String> words = Arrays.asList(answer.split(" "));
            for (String word: words) {
                if (caseInsensitive == true) {
                    specifications.add(answerContainsCaseInsensitive(word));
                } else {
                    specifications.add(answerContains(word));
                }
            }
        }
        if (isEnabled(executor)) {
            specifications.add(hasExecutor(executor));
        }
        if (executeDate != null) {
            specifications.add(hasExecutDate(executeDate));
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
                || outNumber != null
                || smav != null
                || isEnabled(theme)
                || isEnabled(answer)
                || isEnabled(executor)
                || executeDate != null) {
            return true;
        }
        return false;
    }

    private Boolean isEnabled(String s) {
        return s != null && !s.isEmpty();
    }
}
