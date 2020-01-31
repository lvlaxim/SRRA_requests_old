package ru.lastenko.maxim.SRRA_requests.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnloadFilter {
    private int sourceId;
    private int executorId;
    private boolean isEntity;
    private String dateFrom;
    private String dateTo;
    private String[] rubrics;
}
