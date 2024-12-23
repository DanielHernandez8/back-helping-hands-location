package com.helpinghandslocation.helpinghandslocation.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = true)
    private String lastName;
    @Column(nullable = true)
    private String phoneNumber;


    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = true)
    private Type type;

    public User(long id, String username, String password, String email, String firstName, String lastName, String phoneNumber, boolean b, boolean b1, boolean b2, boolean b3, Type particular) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.accountNonExpired = b;
        this.accountNonLocked = b1;
        this.credentialsNonExpired = b2;
        this.enabled = b3;
        this.type = particular;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Devolvemos un ArrayList vacío porque nuestra app no tiene roles
        return new ArrayList<>();
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Location> locations;
}

