package org.example.custodiacrm.service.Impl;

import org.example.custodiacrm.exceptions.ResourceNotFoundException;
import org.example.custodiacrm.models.dto.CreateNoteDTO;
import org.example.custodiacrm.models.entities.Customer;
import org.example.custodiacrm.models.entities.Note;
import org.example.custodiacrm.models.entities.Opportunity;
import org.example.custodiacrm.models.entities.User;
import org.example.custodiacrm.repositories.CustomerRepository;
import org.example.custodiacrm.repositories.NoteRepository;
import org.example.custodiacrm.repositories.OpportunityRepository;
import org.example.custodiacrm.repositories.UserRepository;
import org.example.custodiacrm.service.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class NoteServiceImpl implements NoteService {



    private final NoteRepository noteRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final OpportunityRepository opportunityRepository;

    public NoteServiceImpl(NoteRepository noteRepository, CustomerRepository customerRepository, UserRepository userRepository, OpportunityRepository opportunityRepository) {
        this.noteRepository = noteRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.opportunityRepository = opportunityRepository;
    }


    @Override
    public void addNoteToCustomer(CreateNoteDTO createNoteDTO) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Customer customer = customerRepository.findByEmail(createNoteDTO.getCustomerEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Customer with provided email not found"));


        Opportunity opportunity = opportunityRepository.findTopByCustomerOrderByCreatedAtDesc(customer)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No opportunity found for customer with email: " + createNoteDTO.getCustomerEmail()
                ));

        Note note = Note.builder()
                .content(createNoteDTO.getContent())
                .author(currentUser)
                .customer(customer)
                .opportunity(opportunity)
                .build();

        noteRepository.save(note);

    }
}
