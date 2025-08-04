package org.example.custodiacrm.models.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.custodiacrm.models.enums.OpportunityStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "opportunities")
@Data
@EqualsAndHashCode(callSuper = true)
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

    @Builder.Default
    @OneToMany(mappedBy = "opportunity", cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<>();

}
