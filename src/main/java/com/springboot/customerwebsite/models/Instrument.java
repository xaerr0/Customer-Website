package com.springboot.customerwebsite.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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


    @OneToMany(mappedBy = "instrument")
    private List<Customer> customers;


    public Integer getOnHand() {
        onHand = totalInventory - rentedOut;
        return onHand;
    }


    public void rentInstrument(Customer customer) {
        customer.setInstrument(this);
        customers.add(customer);
        rentedOut++;
    }

    public void returnInstrument(Customer customer) {
        customers.remove(customer);
        customer.setInstrument(null);
        rentedOut--;
    }

    @Override
    public String toString() {
        return (brand + " - " + name + " (" + price + ")");
    }
}