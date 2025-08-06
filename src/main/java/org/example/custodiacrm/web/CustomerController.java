package org.example.custodiacrm.web;


import org.example.custodiacrm.models.dto.CreateCustomerDTO;
import org.example.custodiacrm.models.dto.GetCustomersDTO;
import org.example.custodiacrm.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody CreateCustomerDTO dto) {
        customerService.createCustomer(dto);
        return ResponseEntity.ok("Customer created successfully");
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<GetCustomersDTO>> getAllCustomers() {
        List<GetCustomersDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
}
