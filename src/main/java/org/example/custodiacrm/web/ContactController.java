package org.example.custodiacrm.web;


import org.example.custodiacrm.models.dto.CreateContactDTO;
import org.example.custodiacrm.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/contacts/add")
    public ResponseEntity<String> addContactToCustomer(@RequestBody CreateContactDTO contactDTO) {
        try {
            contactService.addContactToCustomer(contactDTO);
            return ResponseEntity.ok("Contact added to customer successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
