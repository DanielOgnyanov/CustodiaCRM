package org.example.custodiacrm.service;

import org.example.custodiacrm.models.dto.CreateContactDTO;

public interface ContactService {

    void addContactToCustomer(CreateContactDTO createContactDTO);

}
