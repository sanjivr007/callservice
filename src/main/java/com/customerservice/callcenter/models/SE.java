package com.customerservice.callcenter.models;

import com.customerservice.callcenter.handler.CallHandler;
import com.customerservice.callcenter.model.entity.EmployeeRank;

public class SE extends Employee {
    public SE(CallHandler callHandler, int escalationThreshold, int id) {
        super(callHandler, escalationThreshold, id);
        rank = EmployeeRank.SE;
    }

}