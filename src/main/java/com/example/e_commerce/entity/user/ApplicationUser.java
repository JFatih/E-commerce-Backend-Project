package com.example.e_commerce.entity.user;

import com.example.e_commerce.entity.user.orders.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="app_user",schema="public")
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotEmpty
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="app_user_role",schema="ecommerce",
                joinColumns = @JoinColumn(name="app_user_id"),
                inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> authorities;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="store_id")
    private Store store;

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL)
    private List<CardDetails> cards;

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL)
    private List<Order> orders;

    public void addAuthority(Role role){
        if(authorities == null){
            authorities = new HashSet<>();
        }
        authorities.add(role);
    }

    public void addCard(CardDetails card){
        if(cards == null){
            cards = new ArrayList<>();
        }
        cards.add(card);
    }

    public void addOrder(Order order){
        if(orders == null){
            orders = new ArrayList<>();
        }
        orders.add(order);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
