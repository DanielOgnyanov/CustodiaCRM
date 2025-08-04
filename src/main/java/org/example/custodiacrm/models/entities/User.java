package org.example.custodiacrm.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.example.custodiacrm.models.enums.UserRole;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {


    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Pattern(regexp = "^[\\d\\-\\+]{9,15}$", message = "Phone number format is invalid")
    private String phoneNumber;

    private LocalDateTime createdAt;

    @PrePersist

    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder.Default
    @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Customer> customers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "assignedUser")
    private List<Contact> contacts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.ALL)
    private List<Opportunity> opportunities = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<>();

}
