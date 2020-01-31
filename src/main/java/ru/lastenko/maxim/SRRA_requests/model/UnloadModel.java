package ru.lastenko.maxim.SRRA_requests.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnloadModel {
    String rubricCode;
    int copyNumber;
    int countOfRequests;
}
