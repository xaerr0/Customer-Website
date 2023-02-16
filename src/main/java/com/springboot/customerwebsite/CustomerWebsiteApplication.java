package com.springboot.customerwebsite;

import com.springboot.customerwebsite.models.Customer;
import com.springboot.customerwebsite.models.Instrument;
import com.springboot.customerwebsite.models.Role;
import com.springboot.customerwebsite.models.User;
import com.springboot.customerwebsite.repositories.RoleRepo;
import com.springboot.customerwebsite.repositories.UserRepo;
import com.springboot.customerwebsite.services.CustomerService;
import com.springboot.customerwebsite.services.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

import static com.springboot.customerwebsite.models.Role.Roles.ROLE_ADMIN;
import static com.springboot.customerwebsite.models.Role.Roles.ROLE_USER;


@SpringBootApplication
public class CustomerWebsiteApplication implements CommandLineRunner {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    // The main method is defined here which will start your application
    public static void main(String[] args) {
        SpringApplication.run(CustomerWebsiteApplication.class, args);
    }

    // You can also define a run method which performs an operation at runtime
    // In this example, the run method saves some Customer data into the database for testing
    @Override
    public void run(String... args) throws Exception {

        Role user = Role.builder().role(ROLE_USER).build();
        Role admin = Role.builder().role(ROLE_ADMIN).build();


        if (roleRepo.findAll().isEmpty()) {
            roleRepo.saveAll(Arrays.asList(user, admin));
        }

        if (userRepo.findAll().isEmpty()) {
            User superUser  = User.builder()
                    .username("admin")
                    .email("admin@email.com")
                    .password(passwordEncoder.encode("admin"))
                    .isAccountNonExpired(true)
                    .isEnabled(true)
                    .isCredentialsNonExpired(true)
                    .isAccountNonLocked(true)
                    .role(admin)
                    .build();
            userRepo.save(superUser);
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