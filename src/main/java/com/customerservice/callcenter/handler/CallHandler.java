package com.customerservice.callcenter.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.customerservice.callcenter.models.Call;
import com.customerservice.callcenter.models.Constants;
import com.customerservice.callcenter.models.Customer;
import com.customerservice.callcenter.models.Employee;
import com.customerservice.callcenter.models.JE;
import com.customerservice.callcenter.models.Manager;
import com.customerservice.callcenter.models.SE;



public class CallHandler {

    private static CallHandler callHandler = null;
    private int numberOfJE;
    private int numberOfSE;
    private int numberOfManager;
    private int totalCalls;
    Integer jEDurationMatrix [][];
    Integer sEDurationMatrix [][] ;
    Integer mgrDurationMatrix [][];
    private int MAX_CALL_PER_EMPLOYEE;
    private List<PriorityQueue<Employee>> employeeLevels;
    private List<List<Call>> callQueues;

    private CallHandler() {

    }


    public static synchronized CallHandler getInstance(int numberOfJE, int numberOfSE, int numberOfManager, int totalCalls) {
        if (callHandler == null) {
            callHandler = new CallHandler();
            callHandler.numberOfJE = numberOfJE;
            callHandler.numberOfSE = numberOfSE;
            callHandler.numberOfManager = numberOfManager;
            callHandler.totalCalls = totalCalls;
            callHandler.MAX_CALL_PER_EMPLOYEE = totalCalls / (numberOfJE + numberOfSE);
            callHandler.employeeLevels = new ArrayList<PriorityQueue<Employee>>(Constants.NO_OF_LEVEL);

            PriorityQueue<Employee> jEs = new PriorityQueue<Employee>(numberOfJE);
            for (int k = 0; k < numberOfJE; k++) {
                jEs.add(new JE(callHandler, Constants.JE_ESCALATION_TIME,k+1));
            }
            callHandler.employeeLevels.add(jEs);

            PriorityQueue<Employee> sEs = new PriorityQueue<Employee>(numberOfSE);
            for (int k = 0; k < numberOfSE; k++) {
                sEs.add(new SE(callHandler, Constants.SE_ESCALATION_TIME,k+1));
            }
            callHandler.employeeLevels.add(sEs);

            PriorityQueue<Employee> managers = new PriorityQueue<Employee>(2);
            managers.add(new Manager(callHandler, Constants.MGR_ESCALATION_TIME,1));
            callHandler.employeeLevels.add(managers);

        }
        return callHandler;
    }


    public Employee getHandlerForCall(Call call) {
        int level = call.getRank().getValue();
        PriorityQueue<Employee> employeeLevel = employeeLevels.get(level);
        Employee emp = employeeLevel.poll();

        if (emp != null && emp.isFree()) {
            return emp;
        }
        return null;
    }

    public void dispatchCall(Customer caller) {
        Call call = new Call(caller);
        dispatchCall(call);
    }

    public void dispatchCall(Call call) {
        Employee emp = getHandlerForCall(call);
        if (emp != null) {
            if (emp.getNumberOfCallReceived() < MAX_CALL_PER_EMPLOYEE) {

                emp.receiveCall(call);
                emp.callCompleted();
            }
            employeeLevels.get(emp.getRank().getValue()).add(emp);
        } else {
            call.reply("Please wait for free " + call.getRank() + " employee to reply");
        }
    }

    public int getMAX_CALL_PER_EMPLOYEE() {
        return MAX_CALL_PER_EMPLOYEE;
    }

    public List<PriorityQueue<Employee>> getEmployeeLevels() {
        return employeeLevels;
    }

    public void setjEDurationMatrix(Integer[][] jEDurationMatrix) {
        this.jEDurationMatrix = jEDurationMatrix;
    }

    public void setsEDurationMatrix(Integer[][] sEDurationMatrix) {
        this.sEDurationMatrix = sEDurationMatrix;
    }

    public void setMgrDurationMatrix(Integer[][] mgrDurationMatrix) {
        this.mgrDurationMatrix = mgrDurationMatrix;
    }


    public Integer[][] getjEDurationMatrix() {
        return jEDurationMatrix;
    }

    public Integer[][] getsEDurationMatrix() {
        return sEDurationMatrix;
    }

    public Integer[][] getMgrDurationMatrix() {
        return mgrDurationMatrix;
    }

}