package com.example.tiendagym.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Venta {

    @Id
    @GeneratedValue
    private Long id;

    private String fecha_pedido;

    private String informacion;

    @ManyToOne
    @JoinColumn()
    private Cliente cliente;

    @JsonBackReference
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private Set<ProductoVenta> productoVentas = new HashSet<>();

    public Venta(String fecha_pedido, String informacion, Cliente cliente) {
        this.fecha_pedido = fecha_pedido;
        this.informacion = informacion;
        this.cliente = cliente;
    }


    public Venta() {
    }
}
