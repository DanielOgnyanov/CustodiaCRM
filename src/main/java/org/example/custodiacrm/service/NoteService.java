package org.example.custodiacrm.service;

import org.example.custodiacrm.models.dto.CreateNoteDTO;

public interface NoteService {
    void addNoteToCustomer(CreateNoteDTO createNoteDTO);
}
