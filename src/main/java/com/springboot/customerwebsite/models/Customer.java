package com.springboot.customerwebsite.models;

import lombok.*;

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

    private String fullName;
    private String emailAddress;
    private Integer age;
    private String address;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;

    public void setInstrument() {
    }
}