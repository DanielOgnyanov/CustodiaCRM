package org.example.custodiacrm.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetNoteDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;

    private String authorUsername;
    private String customerEmail;
    private String opportunityTitle;
}
