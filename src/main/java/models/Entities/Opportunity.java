package models.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Enums.OpportunityStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "opportunities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Opportunity extends BaseEntity{
    @NotBlank(message = "Title is required")
    private String title;


    private String description;

    @NotNull(message = "Estimated value is required")
    private double estimatedValue;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private OpportunityStatus status;

    @ManyToOne(optional = false)
    private Customer customer;

    @ManyToOne(optional = false)
    private User assignedUser;

    private LocalDateTime createdAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}
