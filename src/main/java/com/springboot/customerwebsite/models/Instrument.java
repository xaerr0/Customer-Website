package com.springboot.customerwebsite.models;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "instruments")
@Builder
@Getter
@Setter
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String type;
    private String brand;
    private Double price;
    private Integer totalInventory;
    private Integer onHand;
    private Integer rentedOut;


    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    private Integer getOnHand() {
        Integer onHand = totalInventory - rentedOut;
        return this.onHand;
    }

    private Integer getRentedOut() {
        Integer rentedOut = totalInventory - onHand;
        return this.rentedOut;
    }
}