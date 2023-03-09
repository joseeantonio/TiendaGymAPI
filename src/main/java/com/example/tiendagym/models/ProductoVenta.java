package com.example.tiendagym.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class ProductoVenta {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn()
    private Venta venta;

    @ManyToOne
    @JoinColumn()
    private Producto producto;

    private Integer cantidad;


    public ProductoVenta(Venta venta, Producto producto, Integer cantidad) {
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public ProductoVenta() {
    }
}
