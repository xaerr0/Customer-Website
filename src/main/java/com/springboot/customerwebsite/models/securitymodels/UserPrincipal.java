package com.springboot.customerwebsite.models.securitymodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.customerwebsite.models.UserMeta;
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

    @OneToOne(cascade = CascadeType.PERSIST, optional = false)
    private UserMeta userMeta;

    public UserPrincipal(String username, String email, String password, List<Authority> authorities, UserMeta userMeta) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.userMeta = userMeta;


    }
}