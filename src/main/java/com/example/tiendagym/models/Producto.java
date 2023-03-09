package com.example.tiendagym.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity @Getter @Setter
public class Producto {


    @Id
    @GeneratedValue
    private Long id;

    private Number size;

    private String name;

    private String description;

    private String image;

    private String category;

    private String marca;

    @JsonBackReference
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private Set<ProductoVenta> productoVentas = new HashSet<>();

    public Producto() {
    }

    public Producto(Number size, String name, String description, String image) {
        this.size = size;
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
