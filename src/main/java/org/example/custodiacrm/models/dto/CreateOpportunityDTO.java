package org.example.custodiacrm.models.dto;


import lombok.Data;
import org.example.custodiacrm.models.enums.OpportunityStatus;

@Data
public class CreateOpportunityDTO {

    private String title;
    private String description;
    private double estimatedValue;
    private OpportunityStatus status;
    private String customerEmail;
}
