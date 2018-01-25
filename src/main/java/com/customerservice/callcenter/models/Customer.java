package com.customerservice.callcenter.models;



import com.customerservice.callcenter.model.entity.CustomerGrade;
import com.customerservice.callcenter.model.entity.ServiceType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Customer {
    private String firstName;
    private String secondName;
    private String registeredMobileNumber;
    private CustomerGrade customerGrade;
    private ServiceType serviceType;
}
