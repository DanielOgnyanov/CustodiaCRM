package org.example.custodiacrm.models.dto;

import lombok.Data;

@Data
public class CreateContactDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String notes;

}
