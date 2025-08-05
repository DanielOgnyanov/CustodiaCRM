package org.example.custodiacrm.repositories;


import org.example.custodiacrm.models.entities.Customer;
import org.example.custodiacrm.models.entities.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
    Optional<Opportunity> findTopByCustomerOrderByCreatedAtDesc(Customer customer);

}
