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

    @Transient
    private Integer onHand;

    private Integer rentedOut;


    @OneToMany
    @JoinColumn(name = "customer_id")
    private Customer customer;



    public Integer getOnHand() {
        onHand = totalInventory - rentedOut;
        return onHand;
    }


    public void rentInstrument(Customer customer) {
        customer.setInstrument(this);
        this.customer = customer;
        rentedOut++;
    }

    public void returnInstrument() {
        customer = null;
        rentedOut--;
    }

    @Override
    public String toString() {
        return (brand + " - " + name + " (" + price + ")");
    }
}