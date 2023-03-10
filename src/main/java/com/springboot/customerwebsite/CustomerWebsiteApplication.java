package com.springboot.customerwebsite;


import com.springboot.customerwebsite.models.Customer;
import com.springboot.customerwebsite.models.securitymodels.Authority;
import com.springboot.customerwebsite.models.securitymodels.AuthorityEnum;
import com.springboot.customerwebsite.models.securitymodels.UserPrincipal;
import com.springboot.customerwebsite.repositories.AuthorityRepo;
import com.springboot.customerwebsite.repositories.CustomerRepo;
import com.springboot.customerwebsite.repositories.UserPrincipalRepo;
import com.springboot.customerwebsite.services.CustomerService;
import com.springboot.customerwebsite.services.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;


@SpringBootApplication
public class CustomerWebsiteApplication implements CommandLineRunner {

    @Autowired
    AuthorityRepo authorityRepo;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    UserPrincipalRepo userPrincipalRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private InstrumentService instrumentService;

    // The main method is defined here which will start your application
    public static void main(String[] args) {
        SpringApplication.run(CustomerWebsiteApplication.class, args);
    }

    // You can also define a run method which performs an operation at runtime
    // In this example, the run method saves some Customer data into the database for testing
    @Override
    public void run(String... args) throws Exception {


        Authority userAuth = Authority.builder().authority(AuthorityEnum.ROLE_USER).build();
        Authority adminAuth = Authority.builder().authority(AuthorityEnum.ROLE_ADMIN).build();


        if (authorityRepo.findAll().isEmpty()) {
            authorityRepo.saveAll(Arrays.asList(userAuth, adminAuth));
        }


        if (userPrincipalRepo.findAll().isEmpty()) {

            Customer customer = Customer.builder()
                    .firstName("Customer First Name")
                    .lastName("Customer Last Name")
                    .address("Customer Address One")
                    .age(30)
                    .build();
            customerRepo.save(customer);

            UserPrincipal superUser = UserPrincipal.builder()
                    .username("admin")
                    .email("admin@email.com")
                    .password(passwordEncoder.encode("adminadmin"))
                    .isAccountNonExpired(true)
                    .isEnabled(true)
                    .isCredentialsNonExpired(true)
                    .isAccountNonLocked(true)
                    .authorities(Arrays.asList(userAuth, adminAuth))
                    .customer(customer)
                    .build();

            userPrincipalRepo.save(superUser);

        }


////        customerService.deleteAllCustomers();
////        instrumentService.deleteAllInstruments();
//
//        if (customerService.getAllCustomers().isEmpty())
//            customerService.saveAllCustomers(Arrays.asList(
//                            Customer.builder()
//                                    .fullName("Customer 1")
//                                    .emailAddress("customer1@gmail.com")
//                                    .address("Customer Address One")
//                                    .age(30)
//                                    .build(),
//                            Customer.builder().fullName("Customer 2").emailAddress("customer2@gmail.com")
//                                    .address("Customer Address Two").age(28).build(),
//                            Customer.builder().fullName("Customer 3").emailAddress("customer3@gmail.com")
//                                    .address("Customer Address Three").age(32).build()
//                    )
//            );
//
//        if (instrumentService.getAllInstruments().isEmpty()) {
//            instrumentService.saveAllInstruments(Arrays.asList(
//                    Instrument.builder()
//                            .name("Violin")
//                            .type("String")
//                            .brand("Stradivarius")
//                            .price(399.99)
//                            .totalInventory(2)
//                            .onHand(1)
//                            .rentedOut(1)
//                            .build(),
//                    Instrument.builder()
//                            .name("Trumpet")
//                            .type("Brass")
//                            .brand("Bach")
//                            .price(299.99)
//                            .totalInventory(4)
//                            .onHand(1)
//                            .rentedOut(1)
//                            .build(),
//                    Instrument.builder()
//                            .name("Clarinet")
//                            .type("Woodwind")
//                            .brand("Yamaha")
//                            .price(159.99)
//                            .totalInventory(5)
//                            .onHand(1)
//                            .rentedOut(1)
//                            .build())
//            );
//        }


    }
}