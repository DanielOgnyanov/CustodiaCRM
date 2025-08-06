package org.example.custodiacrm.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.custodiacrm.models.enums.CustomerStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCustomersDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String companyName;

    private String address;

    private CustomerStatus status;

    private String assignedUserUsername;

    private List<GetContactDTO> contacts;

    private List<GetOpportunityDTO> opportunities;

    private List<GetNoteDTO> notes;
}
