package org.example.custodiacrm.service.Impl;

import org.example.custodiacrm.models.dto.CreateNoteDTO;
import org.example.custodiacrm.models.entities.Customer;
import org.example.custodiacrm.models.entities.Note;
import org.example.custodiacrm.models.entities.User;
import org.example.custodiacrm.repositories.CustomerRepository;
import org.example.custodiacrm.repositories.NoteRepository;
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

    public NoteServiceImpl(NoteRepository noteRepository, CustomerRepository customerRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void addNoteToCustomer(CreateNoteDTO createNoteDTO) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Customer customer = customerRepository.findByEmail(createNoteDTO.getCustomerEmail())
                .orElseThrow(() -> new RuntimeException("Customer not found"));


        Note note = Note.builder()
                .content(createNoteDTO.getContent())
                .author(currentUser)
                .customer(customer)
                .build();

        noteRepository.save(note);

    }
}
