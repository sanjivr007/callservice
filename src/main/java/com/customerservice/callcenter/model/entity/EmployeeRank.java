package com.customerservice.callcenter.model.entity;

public enum EmployeeRank {
    JE(0), SE(1), MANAGER(2);
    private int value;

    EmployeeRank(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }
}