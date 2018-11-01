package com.nastinio.spring.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @Column(name = "id_manager")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message="Введите логин")
    @Pattern(regexp = "^\\d+$", message = "Неверный формат логина")
    public Integer id;

    @NotNull(message="Введите пароль")
    private String password;

    public Manager(){}

    @Override
    public String toString() {
        return "id = " + id + ", password = " + password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
