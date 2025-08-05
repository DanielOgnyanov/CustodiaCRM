package org.example.custodiacrm.services;

import org.example.custodiacrm.models.dto.CreateCustomerDTO;
import org.example.custodiacrm.models.entities.Customer;
import org.example.custodiacrm.models.entities.User;
import org.example.custodiacrm.models.enums.CustomerStatus;
import org.example.custodiacrm.repositories.CustomerRepository;
import org.example.custodiacrm.repositories.UserRepository;
import org.example.custodiacrm.service.Impl.CustomerServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
    }

    @Test
    void shouldCreateCustomerSuccessfully() {

        CreateCustomerDTO dto = new CreateCustomerDTO();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setEmail("john.doe@example.com");
        dto.setPhoneNumber("1234567890");
        dto.setCompanyName("Acme Inc.");
        dto.setAddress("123 Street");
        dto.setStatus(CustomerStatus.ACTIVE);

        User user = new User();
        user.setUsername("johnuser");

        Mockito.when(authentication.getName()).thenReturn("johnuser");
        Mockito.when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));
        Mockito.when(customerRepository.save(Mockito.any(Customer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        customerService.createCustomer(dto);


        Mockito.verify(customerRepository, Mockito.times(1)).save(Mockito.any(Customer.class));
    }
}
