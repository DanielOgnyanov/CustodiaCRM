package org.example.custodiacrm.service.Impl;

import org.example.custodiacrm.models.dto.CreateContactDTO;
import org.example.custodiacrm.models.entities.Contact;
import org.example.custodiacrm.models.entities.Customer;
import org.example.custodiacrm.repositories.ContactRepository;
import org.example.custodiacrm.repositories.CustomerRepository;
import org.example.custodiacrm.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final CustomerRepository customerRepository;

    public ContactServiceImpl(ContactRepository contactRepository, CustomerRepository customerRepository) {
        this.contactRepository = contactRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public void addContactToCustomer(CreateContactDTO createContactDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(createContactDTO.getEmail());

        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("Customer with email '" + createContactDTO.getEmail() + "' not found.");
        }

        Customer customer = optionalCustomer.get();

        Contact contact = Contact.builder()
                .firstName(createContactDTO.getFirstName())
                .lastName(createContactDTO.getLastName())
                .email(createContactDTO.getEmail())
                .phoneNumber(createContactDTO.getPhoneNumber())
                .notes(createContactDTO.getNotes())
                .customer(customer)
                .build();

        contactRepository.save(contact);
    }
}
