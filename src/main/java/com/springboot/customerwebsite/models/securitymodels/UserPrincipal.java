package com.springboot.customerwebsite.models.securitymodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.customerwebsite.models.Customer;
//import com.springboot.customerwebsite.models.UserMeta;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user_details")
public class UserPrincipal implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority_join_table",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private List<Authority> authorities;

    @Column(nullable = false)
    private boolean isAccountNonExpired = true;

    @Column(nullable = false)
    private boolean isAccountNonLocked = true;

    @Column(nullable = false)
    private boolean isCredentialsNonExpired = true;

    @Column(nullable = false)
    private boolean isEnabled = true;

    //TODO Had to change this to CasecadeType.MERGE from PERSIST to get the app to run. What exactly does that do?
    @OneToOne(cascade = CascadeType.MERGE, optional = true)
    private Customer customer;

    public UserPrincipal(String username, String email, String password, List<Authority> authorities, Customer customer) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.customer = customer;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}