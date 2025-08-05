package org.example.custodiacrm.service.Impl;

import org.example.custodiacrm.models.dto.CreateCustomerDTO;
import org.example.custodiacrm.models.entities.Customer;
import org.example.custodiacrm.models.entities.User;
import org.example.custodiacrm.repositories.CustomerRepository;
import org.example.custodiacrm.repositories.UserRepository;
import org.example.custodiacrm.service.CustomerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void createCustomer(CreateCustomerDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("A customer with this email already exists.");
        }

        Customer customer = Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .companyName(dto.getCompanyName())
                .address(dto.getAddress())
                .status(dto.getStatus())
                .assignedUser(currentUser)
                .build();

        customerRepository.save(customer);

    }
}
