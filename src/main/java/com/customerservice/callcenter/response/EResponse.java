package com.customerservice.callcenter.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EResponse {

    private String id;
    private String timeTakenInMinutes;
    private String callsAttended;
    private String resolved;
    private String escalated;
}