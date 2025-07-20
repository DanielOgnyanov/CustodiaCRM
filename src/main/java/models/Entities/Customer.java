package models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Enums.CustomerStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();
}
