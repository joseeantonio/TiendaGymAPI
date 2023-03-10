package com.example.tiendagym.controllers;

import com.example.tiendagym.models.Cliente;
import com.example.tiendagym.models.ProductoVenta;
import com.example.tiendagym.models.Venta;
import com.example.tiendagym.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class VentaController {

    @Autowired
    VentaRepository ventaRepository;

    @GetMapping("/ventas/")
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(ventaRepository.findAll(), HttpStatus.OK);
    }


    @GetMapping("/ventas/{id}/")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ventaRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/venta/create/")
    public ResponseEntity<Object> create(@RequestBody Venta venta) {
        ventaRepository.save(venta);
        return new ResponseEntity<>(venta, HttpStatus.OK);
    }

    @DeleteMapping("/venta/{id}/")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<Venta> venta = ventaRepository.findById(id);
        venta.ifPresent(value -> ventaRepository.delete(value));
        return new ResponseEntity<>(venta.isPresent(), HttpStatus.OK);
    }

    @PutMapping("/venta/{id}/")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Venta venta) {
        Optional<Venta> oldPrisoner = ventaRepository.findById(id);
        if(oldPrisoner.isPresent()) {
            venta.setId(id);
            ventaRepository.save(venta);
            return new ResponseEntity<>(venta, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

}
