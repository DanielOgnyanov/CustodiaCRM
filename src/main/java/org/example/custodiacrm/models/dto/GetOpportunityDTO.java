package org.example.custodiacrm.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.custodiacrm.models.enums.OpportunityStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOpportunityDTO {
    private Long id;
    private String title;
    private String description;
    private double estimatedValue;
    private OpportunityStatus status;
    private LocalDateTime createdAt;

    private String customerEmail;
    private String assignedUsername;
}
