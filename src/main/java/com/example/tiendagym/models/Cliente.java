package com.example.tiendagym.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity @Getter @Setter
public class Cliente {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;

    private String username;

    @JsonBackReference
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Set<Venta> ventas = new HashSet<>();


    public Cliente(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public Cliente() {
    }
}
