package org.example.custodiacrm.web;


import lombok.RequiredArgsConstructor;
import org.example.custodiacrm.models.dto.CreateCustomerDto;
import org.example.custodiacrm.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody CreateCustomerDto dto) {
        customerService.createCustomer(dto);
        return ResponseEntity.ok("Customer created successfully");
    }
}
