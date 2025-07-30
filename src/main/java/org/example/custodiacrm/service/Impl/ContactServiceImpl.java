package org.example.custodiacrm.service.Impl;

import org.example.custodiacrm.repositories.ContactRepository;
import org.example.custodiacrm.service.ContactService;
import org.springframework.stereotype.Service;


@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


}
