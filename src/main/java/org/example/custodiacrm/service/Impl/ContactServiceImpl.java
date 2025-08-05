package org.example.custodiacrm.service.Impl;

import org.example.custodiacrm.exceptions.ResourceConflictException;
import org.example.custodiacrm.exceptions.ResourceNotFoundException;
import org.example.custodiacrm.models.dto.CreateContactDTO;
import org.example.custodiacrm.models.entities.Contact;
import org.example.custodiacrm.models.entities.Customer;
import org.example.custodiacrm.models.entities.User;
import org.example.custodiacrm.repositories.ContactRepository;
import org.example.custodiacrm.repositories.CustomerRepository;
import org.example.custodiacrm.repositories.UserRepository;
import org.example.custodiacrm.service.ContactService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public ContactServiceImpl(ContactRepository contactRepository, CustomerRepository customerRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void addContactToCustomer(CreateContactDTO createContactDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(createContactDTO.getEmail());

        if (optionalCustomer.isEmpty()) {
            throw new ResourceConflictException("Customer with email '" + createContactDTO.getEmail() + "' not found.");
        }

        Customer customer = optionalCustomer.get();


        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();


        User assignedUser = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Contact contact = Contact.builder()
                .firstName(createContactDTO.getFirstName())
                .lastName(createContactDTO.getLastName())
                .email(createContactDTO.getEmail())
                .phoneNumber(createContactDTO.getPhoneNumber())
                .notes(createContactDTO.getNotes())
                .customer(customer)
                .assignedUser(assignedUser)
                .build();

        contactRepository.save(contact);
    }

}
