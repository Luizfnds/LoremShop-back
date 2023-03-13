package com.lefnds.loremshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lefnds.loremshop.enums.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName roleName;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }
}
