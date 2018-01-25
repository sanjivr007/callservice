package com.customerservice.callcenter.call;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.customerservice.callcenter.model.entity.ServiceType;
import com.customerservice.callcenter.models.Call;
import com.customerservice.callcenter.models.Customer;

import static org.junit.Assert.assertEquals;

public class CallTests {

	@Mock
	private Customer customer;

	@Mock
	private Call call;

	@Before
	public void setUp() throws Exception {
	//	int callDuration=5;
		customer = new Customer();
		customer.setFirstName("Sanjiv");
		customer.setSecondName("Ranjan");
		customer.setServiceType(ServiceType.WEBSITE);

		call = new Call(customer);

	}

	@Test
	public void checkRank() {
		System.out.println(call.getRank());
		assertEquals(0, call.getRank().getValue());

	}

	@Test
	public void incrementRank() {
		assertEquals(1, call.incrementRank().getValue());

	}

}
