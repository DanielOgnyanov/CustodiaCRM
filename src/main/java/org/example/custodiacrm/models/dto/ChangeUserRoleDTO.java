package org.example.custodiacrm.models.dto;

import lombok.Data;

@Data
public class ChangeUserRoleDTO {
    private String username;
    private String newRole;
}
