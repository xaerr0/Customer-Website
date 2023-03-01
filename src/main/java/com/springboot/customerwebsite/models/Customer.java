package com.springboot.customerwebsite.models;

import com.springboot.customerwebsite.models.securitymodels.UserPrincipal;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
@Builder
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @Column(nullable = false)
    private String firstName;

//    @Column(nullable = false)
    private String lastName;

//    @Column(nullable = false)
    private Integer age;

//    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;

    public void setInstrument() {
    }

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}