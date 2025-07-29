package org.example.custodiacrm.models.dto;


import lombok.Data;
import org.example.custodiacrm.models.enums.CustomerStatus;

@Data
public class CreateCustomerDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String companyName;
    private String address;
    private CustomerStatus status;
}
