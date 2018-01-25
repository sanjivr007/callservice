package com.customerservice.callcenter.executive;

import com.customerservice.callcenter.handler.CallHandler;
import com.customerservice.callcenter.model.entity.ServiceType;
import com.customerservice.callcenter.models.Call;
import com.customerservice.callcenter.models.Customer;
import com.customerservice.callcenter.models.Employee;
import com.customerservice.callcenter.models.JE;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JETests {


    @Mock
    private Employee employee;

    @Mock
    private CallHandler callHandler;

    int callDuration=10;

    @Before
    public void setUp() throws Exception {
        int totalCalls = 30;
        int numberOfJE = 9;
        int numberOfSE = 3;
        int numberOfManager = 1;

        MockitoAnnotations.initMocks(this);
        //System.setOut(new PrintStream(outContent));


        callHandler = CallHandler.getInstance(numberOfJE, numberOfSE, numberOfManager, totalCalls);


    }

    @Test
    public void JEtest(){
        JE je = new JE(callHandler,7,1);
        assertEquals(0,je.getRank().getValue());
    }

}
