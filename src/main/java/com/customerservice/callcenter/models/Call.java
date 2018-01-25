package com.customerservice.callcenter.models;


import com.customerservice.callcenter.model.entity.EmployeeRank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Call {
    private Customer caller;
    private int callDuration;
    private EmployeeRank rank;
    private Employee handler;

    public Call(Customer c) {
        rank = EmployeeRank.JE;
        caller = c;
    }

    public void reply(String message) {
        System.out.println(message);
    }

    public EmployeeRank incrementRank() {
        if (rank == EmployeeRank.JE) {
            rank = EmployeeRank.SE;
        } else if (rank == EmployeeRank.SE) {
            rank = EmployeeRank.MANAGER;
        }
        return rank;
    }

    public void startCall() {
        reply("Thank you for calling. My name is " + handler.getId() + " " + handler.getRank() + " How may i assist you?");
    }

    public void endCall() {
        reply("Thank you for calling. " +
                "Please provide us feedback for improvement. Summary callDuration: " + callDuration);
    }
}