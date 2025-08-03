package org.example.custodiacrm.web;


import org.example.custodiacrm.models.dto.CreateNoteDTO;
import org.example.custodiacrm.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping("/add-to-customer")
    public ResponseEntity<String> addNoteToCustomer(@RequestBody CreateNoteDTO createNoteDTO) {
        try {
            noteService.addNoteToCustomer(createNoteDTO);
            return new ResponseEntity<>("Note added to customer successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add note: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
