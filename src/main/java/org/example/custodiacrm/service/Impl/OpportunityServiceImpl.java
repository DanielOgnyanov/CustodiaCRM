package org.example.custodiacrm.service.Impl;

import org.example.custodiacrm.models.dto.CreateOpportunityDTO;
import org.example.custodiacrm.models.entities.Customer;
import org.example.custodiacrm.models.entities.Opportunity;
import org.example.custodiacrm.models.entities.User;
import org.example.custodiacrm.repositories.CustomerRepository;
import org.example.custodiacrm.repositories.OpportunityRepository;
import org.example.custodiacrm.repositories.UserRepository;
import org.example.custodiacrm.service.OpportunityService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class OpportunityServiceImpl implements OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public OpportunityServiceImpl(OpportunityRepository opportunityRepository, CustomerRepository customerRepository, UserRepository userRepository) {
        this.opportunityRepository = opportunityRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void createOpportunity(CreateOpportunityDTO createOpportunityDTO) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        Customer customer = customerRepository.findByEmail(createOpportunityDTO.getCustomerEmail())
                .orElseThrow(() -> new RuntimeException("Customer with provided email not found"));


        Opportunity opportunity = Opportunity.builder()
                .title(createOpportunityDTO.getTitle())
                .description(createOpportunityDTO.getDescription())
                .estimatedValue(createOpportunityDTO.getEstimatedValue())
                .status(createOpportunityDTO.getStatus())
                .customer(customer)
                .assignedUser(currentUser)
                .build();

        opportunityRepository.save(opportunity);

    }
}
