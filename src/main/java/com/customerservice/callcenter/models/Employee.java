package com.customerservice.callcenter.models;

import java.util.Objects;
import java.util.Random;

import com.customerservice.callcenter.handler.CallHandler;
import com.customerservice.callcenter.model.entity.EmployeeRank;
import com.customerservice.callcenter.model.entity.EmployeeStatus;
import lombok.Data;


@Data
public class Employee implements Comparable<Employee> {


    private final int escalationThreshold;
    EmployeeRank rank;
    private int id;
    private String name;
    private Employee boss;
    private CallHandler callHandler;
    private Call currentCall;
    private EmployeeStatus employeeStatus;
    private int numberOfCallReceived;
    private int totalTimeSpent;
    private int callEscalated;

    public Employee(CallHandler handler, int escalationThreshold,int id) {
        this.numberOfCallReceived = 0;
        this.totalTimeSpent = 0;
        this.callEscalated = 0;
        this.escalationThreshold = escalationThreshold;
        this.id = id;
        callHandler = handler;
        this.currentCall = null;
        this.employeeStatus = EmployeeStatus.Available;
    }

    public void receiveCall(Call call) {
        this.employeeStatus = EmployeeStatus.OnCall;

        currentCall = call;
        call.setHandler(this);
        int duration= getDuration(this.id,this.numberOfCallReceived);
        call.setCallDuration(duration);
        currentCall.startCall();
        this.numberOfCallReceived++;
        this.totalTimeSpent += duration;
        if (duration > this.escalationThreshold) {
            this.callEscalated++;
            escalateAndReassign();
        }
    }

    private int getDuration(int id, int numberOfCallReceived) {
        if(this.getRank().equals(EmployeeRank.JE)){
           // System.out.println(callHandler.getjEDurationMatrix()[0][0]);
            return callHandler.getjEDurationMatrix()[id][numberOfCallReceived+1];
        }else if(this.getRank().equals(EmployeeRank.SE)){
            return callHandler.getsEDurationMatrix()[id][numberOfCallReceived+1];
        }else{
            return callHandler.getMgrDurationMatrix()[1][numberOfCallReceived+1];
        }
    }


    public void callCompleted() {
        if (currentCall != null) {
            currentCall.endCall();
            currentCall = null;
            employeeStatus = EmployeeStatus.Available;
        }

    }

    public void escalateAndReassign() {
        if (currentCall != null && currentCall.getRank() != EmployeeRank.MANAGER) {
            currentCall.setRank(currentCall.incrementRank());
            System.out.println("\n\n call being escalated to  " + currentCall.getRank());
            callHandler.dispatchCall(currentCall);
            currentCall = null;
            employeeStatus = EmployeeStatus.Available;
        }

    }


    public boolean isFree() {
        return (employeeStatus == EmployeeStatus.Available) && currentCall == null;
    }

    public EmployeeRank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return getId() == employee.getId() &&
                Objects.equals(getName(), employee.getName()) &&
                getRank() == employee.getRank();
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getId(), getName(), getRank());
    }

    public int compareTo(Employee e) {
        if (this.numberOfCallReceived == callHandler.getMAX_CALL_PER_EMPLOYEE()) {
            return 1;
        }
        if (this.employeeStatus != EmployeeStatus.Available)
            return -1;

        return (this.numberOfCallReceived > e.numberOfCallReceived)?1:-1;
    }
}