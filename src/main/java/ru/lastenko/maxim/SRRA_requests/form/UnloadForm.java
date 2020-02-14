package ru.lastenko.maxim.SRRA_requests.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnloadForm {
    String rubricCode;
    int copyNumber;
    int countOfRequests;
}
