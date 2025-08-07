package org.example.custodiacrm.service.Impl;

import org.example.custodiacrm.exceptions.ResourceConflictException;
import org.example.custodiacrm.exceptions.ResourceNotFoundException;
import org.example.custodiacrm.models.dto.*;
import org.example.custodiacrm.models.entities.*;
import org.example.custodiacrm.repositories.CustomerRepository;
import org.example.custodiacrm.repositories.UserRepository;
import org.example.custodiacrm.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new ResourceConflictException("A customer with this email already exists.");
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

    @Override
    public List<GetCustomersDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private GetCustomersDTO mapToDTO(Customer customer) {
        return GetCustomersDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .companyName(customer.getCompanyName())
                .address(customer.getAddress())
                .status(customer.getStatus())
                .assignedUserUsername(
                        customer.getAssignedUser() != null ? customer.getAssignedUser().getUsername() : null
                )
                .contacts(customer.getContacts().stream()
                        .map(this::mapContactToDTO)
                        .collect(Collectors.toList()))
                .opportunities(customer.getOpportunities().stream()
                        .map(this::mapOpportunityToDTO)
                        .collect(Collectors.toList()))
                .notes(customer.getNotes().stream()
                        .map(this::mapNoteToDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    private GetContactDTO mapContactToDTO(Contact contact) {
        return GetContactDTO.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phoneNumber(contact.getPhoneNumber())
                .notes(contact.getNotes())
                .createdAt(contact.getCreatedAt())
                .assignedUsername(
                        contact.getAssignedUser() != null ? contact.getAssignedUser().getUsername() : null
                )
                .build();
    }

    private GetOpportunityDTO mapOpportunityToDTO(Opportunity opportunity) {
        return GetOpportunityDTO.builder()
                .id(opportunity.getId())
                .title(opportunity.getTitle())
                .description(opportunity.getDescription())
                .estimatedValue(opportunity.getEstimatedValue())
                .status(opportunity.getStatus())
                .createdAt(opportunity.getCreatedAt())
                .assignedUsername(
                        opportunity.getAssignedUser() != null ? opportunity.getAssignedUser().getUsername() : null
                )
                .build();
    }

    private GetNoteDTO mapNoteToDTO(Note note) {
        return GetNoteDTO.builder()
                .id(note.getId())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .authorUsername(
                        note.getAuthor() != null ? note.getAuthor().getUsername() : null
                )
                .build();
    }
}
