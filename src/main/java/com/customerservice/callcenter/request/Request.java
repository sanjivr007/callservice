package com.customerservice.callcenter.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Data
@NoArgsConstructor
public class Request {

    private int number_of_calls;
    private List<String> je;
    private List<String> se;
    private String mgr;


    public Request(int number_of_calls, List<String> je, List<String> se, String mgr) {
        this.number_of_calls = number_of_calls;
        this.je = je;
        this.se = se;
        this.mgr = mgr;
    }
}
