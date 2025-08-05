package org.example.custodiacrm.web;


import org.example.custodiacrm.models.dto.CreateOpportunityDTO;
import org.example.custodiacrm.service.OpportunityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunityController {

    private final OpportunityService opportunityService;

    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createOpportunity(@RequestBody CreateOpportunityDTO createOpportunityDTO) {
        try {
            opportunityService.createOpportunity(createOpportunityDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Opportunity created successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
