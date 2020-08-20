package ru.gosarhro.SRRA_requests.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
public class UnloadFilter {
    private int[] sourceIds;
    private int executorId;
    private String isEntity;
    private String dateFrom;
    private String dateTo;
    private String[] rubrics;
    private String requestInitiator;
    private String shipment;

    public boolean isCheckedRubric(String rubric) {
        try {
            return Arrays.stream(rubrics).parallel().anyMatch(rubric::equals);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean isCheckedSource(int source) {
        if (sourceIds != null) {
            for (int sourceId : sourceIds) {
                if (sourceId == source)
                    return true;
            }
        }
        return false;
    }
}
