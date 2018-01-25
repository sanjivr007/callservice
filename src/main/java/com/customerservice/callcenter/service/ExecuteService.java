package com.customerservice.callcenter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.stereotype.Component;

import com.customerservice.callcenter.handler.CallHandler;
import com.customerservice.callcenter.models.Customer;
import com.customerservice.callcenter.models.Employee;
import com.customerservice.callcenter.request.Request;
import com.customerservice.callcenter.response.EResponse;
import com.customerservice.callcenter.response.Performance;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class ExecuteService {

    public  Performance processCall() {
        Request request = getDummyRequestObject();
        String jEsCalls[] = request.getJe().get(0).split(",");
        String sEsCalls[] = request.getSe().get(0).split(",");
        Integer jEDurationMatrix [][] = new Integer [request.getJe().size()+1][jEsCalls.length+1];
        Integer sEDurationMatrix [][] = new Integer [request.getSe().size()+1][sEsCalls.length+1];
        Integer mgrDurationMatrix [][] = new Integer[2][request.getMgr().split(",").length+1];

        //init JE Durations
        for (int i = 1; i <= request.getJe().size(); i++) {
            String calls[] = request.getJe().get(i-1).split(",");
            int j = 1;
            for (String jE : calls) {
                jEDurationMatrix[i][j] = Integer.parseInt(jE);
                j++;
            }
        }

        for (int i = 1; i <= request.getSe().size(); i++) {
            String calls[] = request.getSe().get(i-1).split(",");
            int j = 1;
            for (String sE : calls) {
                sEDurationMatrix[i][j] = Integer.parseInt(sE);
                j++;
            }
        }

        String calls[] = request.getMgr().split(",");
        int j = 1;
        for (String mgr : calls) {
            mgrDurationMatrix[1][j] = Integer.parseInt(mgr);
            j++;
        }

        CallHandler callHandler = CallHandler.getInstance(request.getJe().size(),request.getSe().size(), 1, request.getNumber_of_calls());
        callHandler.setjEDurationMatrix(jEDurationMatrix);
        callHandler.setsEDurationMatrix(sEDurationMatrix);
        callHandler.setMgrDurationMatrix(mgrDurationMatrix);


        for (int i = 0; i < request.getNumber_of_calls(); i++) {
                callHandler.dispatchCall(getDummyCustomer());

        }
        return displayResponse(callHandler.getEmployeeLevels());

       // System.out.println("");
    }

    private  Performance displayResponse(List<PriorityQueue<Employee>> employeeLevels) {
        List<EResponse> junior_executives = new ArrayList<>();
        List<EResponse> senior_executives = new ArrayList<>();
        List<EResponse> manager = new ArrayList<>();
        employeeLevels.get(0).stream().forEach(x -> {
            int resol = x.getNumberOfCallReceived() - x.getCallEscalated();
            junior_executives.add(new EResponse(x.getId() + "", x.getTotalTimeSpent() + "", x.getNumberOfCallReceived() + "", resol + "", x.getCallEscalated() + ""));
        });
        employeeLevels.get(1).stream().forEach(x -> {
            int resol = x.getNumberOfCallReceived() - x.getCallEscalated();
            senior_executives.add(new EResponse(x.getId() + "", x.getTotalTimeSpent() + "", x.getNumberOfCallReceived() + "", resol + "", x.getCallEscalated() + ""));
        });

        employeeLevels.get(2).stream().forEach(x -> {
            int resol = x.getNumberOfCallReceived() - x.getCallEscalated();
            manager.add(new EResponse(x.getId() + "", x.getTotalTimeSpent() + "", x.getNumberOfCallReceived() + "", resol + "", x.getCallEscalated() + ""));
        });
        Performance performance = new Performance(manager.get(0), junior_executives, senior_executives);
        /*try {
            System.out.println("new ObjectMapper() = " + new ObjectMapper().writeValueAsString(performance));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
        
        return performance ;
    }

    public  Customer getDummyCustomer() {
        return new Customer();
    }

    public  Request getDummyRequestObject() {

        List<String> je = Arrays.asList("5,7,6,4,6",
                "15,8,7,5,10",
                "7,5,6,14,6",
                "10,4,9,5,12",
                "6,10,11,4,6");

        List<String> se = Arrays.asList("6,14,12,10,5",
                "18,8,6,4,12",
                "8,6,13,7,1");
        String mgr = "30,12,25,13,20,3,3,3,9,2,7,1,7,11,10";

        Request request = new Request(30, je, se, mgr);
        return request;

    }
}