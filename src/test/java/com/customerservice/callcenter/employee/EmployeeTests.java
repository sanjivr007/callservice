package com.customerservice.callcenter.employee;

import static org.junit.Assert.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.customerservice.callcenter.model.entity.EmployeeRank;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.customerservice.callcenter.handler.CallHandler;
import com.customerservice.callcenter.model.entity.ServiceType;
import com.customerservice.callcenter.models.Call;
import com.customerservice.callcenter.models.Customer;
import com.customerservice.callcenter.models.Employee;


public class EmployeeTests {

    @Mock
    private CallHandler callHandler;

	/*@Mock
    private Call call;*/

    @Mock
    private Customer customer;

    @Mock
    private Employee employee;

    int callDuration = 5;

    @Before
    public void setUp() throws Exception {
        int totalCalls = 30;
        int numberOfJE = 9;
        int numberOfSE = 3;
        int numberOfManager = 1;

        MockitoAnnotations.initMocks(this);
        //System.setOut(new PrintStream(outContent));

        Integer jEDurationMatrix[][] = new Integer[5][5];
        Integer sEDurationMatrix[][] = new Integer[5][5];
        Integer mgrDurationMatrix[][] = new Integer[5][5];

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++)
                jEDurationMatrix[i][j] = 1;
        }

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++)
                sEDurationMatrix[i][j] = 1;
        }

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++)
                mgrDurationMatrix[i][j] = 1;
        }


        callHandler = CallHandler.getInstance(numberOfJE, numberOfSE, numberOfManager, totalCalls);

        callHandler.setjEDurationMatrix(jEDurationMatrix);
        callHandler.setsEDurationMatrix(sEDurationMatrix);
        callHandler.setMgrDurationMatrix(mgrDurationMatrix);

        customer = new Customer();
        customer.setFirstName("Sanjiv");
        customer.setSecondName("Ranjan");
        customer.setServiceType(ServiceType.WEBSITE);

        List<Call> calls = new ArrayList<>();
        for (
                int i = 0;
                i < 3; i++)

        {
            Call c = new Call(customer);
            calls.add(c);
        }
        // callHandler.assignTestCall(calls);

        employee = new Employee(callHandler, callDuration, 0);
        employee.setRank(EmployeeRank.JE);


    }

    @Test
    public void isFree() {
        assertEquals(true, employee.isFree());
        assertEquals(null, employee.getCurrentCall());
        assertEquals("Available", employee.getEmployeeStatus().toString());
    }


    @Test
    public void receiveCall() {
        //	Customer customer= new Customer();
        //   Employee employee = new Employee(callHandler);
        employee.receiveCall(new Call(customer));
      //  System.out.println(callHandler.getjEDurationMatrix());
      //  System.out.println(employee.getEmployeeStatus().toString());
        assertEquals("OnCall", employee.getEmployeeStatus().toString());
        assertNotEquals(null, employee.getCurrentCall());

    }

    @Test
    public void escalateAndReassign() {
        employee.escalateAndReassign();

        assertEquals("Available", employee.getEmployeeStatus().toString());
    }


}
