package com.example.e_commerce.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="role",schema="public")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role implements GrantedAuthority  {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleCode code;



    @Override
    public String getAuthority() {
        return code.name();
    }
}
