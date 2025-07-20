package models.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact extends BaseEntity{

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

    private String position;

    @Column(length = 1000)
    private String notes;

    private LocalDateTime createdAt;
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    private User assignedUser;
}
