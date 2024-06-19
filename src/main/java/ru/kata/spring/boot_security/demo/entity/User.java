package ru.kata.spring.boot_security.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min = 2, message = "Имя пользователя не может содержать меньше двух букв")
    @Size(max = 30, message = "Имя пользователя не может содержать больше тридцати букв")
    @NotEmpty(message = "Пользователь не может быть создан без имени")
    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roleSet;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public @Size(min = 2, message = "Имя пользователя не может содержать меньше двух букв")
           @Size(max = 30, message = "Имя пользователя не может содержать больше тридцати букв")
           @NotEmpty(message = "Пользователь не может быть создан без имени")
        String getUsername() {
            return username;
    }

    public void setUsername(@Size(min = 2, message = "Имя пользователя не может содержать меньше двух букв")
                            @Size(max = 30, message = "Имя пользователя не может содержать больше тридцати букв")
                            @NotEmpty(message = "Пользователь не может быть создан без имени")
                            String username) {
        this.username = username;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    /*__________________________________________________________________________________________*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleSet;
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
