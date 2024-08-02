package lk.ijse.hello_shoes_shop_backend.entity;

import jakarta.persistence.*;
import lk.ijse.hello_shoes_shop_backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity implements UserDetails {
    @Id
    private String email;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private EmployeeEntity employeeEntity;

//    @OneToMany(mappedBy = "userEntity")
//    private List<OrderEntity> sales;




    public Role getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
        return authorities;
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