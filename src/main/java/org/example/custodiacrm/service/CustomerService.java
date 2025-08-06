package org.example.custodiacrm.service;

import org.example.custodiacrm.models.dto.CreateCustomerDTO;
import org.example.custodiacrm.models.dto.GetCustomersDTO;

import java.util.List;

public interface CustomerService {
    void createCustomer(CreateCustomerDTO dto);
    List<GetCustomersDTO> getAllCustomers();

}
