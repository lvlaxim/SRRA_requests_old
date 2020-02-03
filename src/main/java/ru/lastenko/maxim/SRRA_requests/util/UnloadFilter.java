package ru.lastenko.maxim.SRRA_requests.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
public class UnloadFilter {
    private int sourceId;
    private int executorId;
    private String isEntity;
    private String dateFrom;
    private String dateTo;
    private String[] rubrics;

    public boolean isCheckedRubric (String rubric) {
        try {
            return Arrays.stream(rubrics).parallel().anyMatch(rubric::equals);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
