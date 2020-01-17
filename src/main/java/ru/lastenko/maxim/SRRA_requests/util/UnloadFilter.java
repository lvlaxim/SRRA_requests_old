package ru.lastenko.maxim.SRRA_requests.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UnloadFilter {
    private String rubricCode;
    private int sourceId;
    private String date;
}
