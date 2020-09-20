package ru.popovichia.eshop.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @NotBlank
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    
    @NotBlank
    @NotNull    
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @NotNull        
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @NotBlank
    @NotNull    
    @Email
    @Column(name = "email")
    private String email;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> listOrders;

    public Customer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<Order> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Order> listOrders) {
        this.listOrders = listOrders;
    }

}
