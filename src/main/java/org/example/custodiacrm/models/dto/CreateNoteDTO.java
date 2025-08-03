package org.example.custodiacrm.models.dto;

import lombok.Data;

@Data
public class CreateNoteDTO {
    private String content;
    private String customerEmail;
}
