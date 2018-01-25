package com.customerservice.callcenter.models;

import com.customerservice.callcenter.handler.CallHandler;
import com.customerservice.callcenter.model.entity.EmployeeRank;

public class JE extends Employee {
    public JE(CallHandler callHandler, int escalationThreshold, int id) {

        super(callHandler, escalationThreshold, id);
        rank = EmployeeRank.JE;
    }

}