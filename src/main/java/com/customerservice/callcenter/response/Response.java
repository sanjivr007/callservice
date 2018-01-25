package com.customerservice.callcenter.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response {

    private int number_of_calls;
    private int resolved;
    private int unresolved;
    private int totalTimeTakenInMinutes;
    private Performance performance;
}
