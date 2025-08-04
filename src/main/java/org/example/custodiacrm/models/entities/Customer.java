package org.example.custodiacrm.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.example.custodiacrm.models.enums.CustomerStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity{

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^[\\d\\-\\+]{9,15}$", message = "Phone number format is invalid")
    private String phoneNumber;


    private String companyName;
    private String address;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @ManyToOne
    private User assignedUser;

    @Builder.Default
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Opportunity> opportunities = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<>();

}
