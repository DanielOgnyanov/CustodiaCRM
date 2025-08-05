package org.example.custodiacrm.models.dto;

import lombok.Data;

@Data
public class ChangeUserRoleDTO {
    private String email;
    private String newRole;
}
