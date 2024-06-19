package ru.kata.spring.boot_security.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min = 2, max = 30, message = "The name must not be less than 2 or more than 30 characters")
    @NotEmpty(message = "The field should not be empty")
    private String username;

    private String password;

    /*private Set<Role> roleSet;*/

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @Size(min = 2, max = 30, message = "The name must not be less than 2 or more than 30 characters") @NotEmpty(message = "The field should not be empty") String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 2, max = 30, message = "The name must not be less than 2 or more than 30 characters") @NotEmpty(message = "The field should not be empty") String username) {
        this.username = username;
    }
}
