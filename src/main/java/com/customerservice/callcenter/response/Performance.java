package com.customerservice.callcenter.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Performance {
    private EResponse manager;
    private List<EResponse> junior_executives;
    private List<EResponse> senior_executives;
}

