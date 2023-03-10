package com.example.tiendagym.controllers;

import com.example.tiendagym.models.Cliente;
import com.example.tiendagym.models.ProductoVenta;
import com.example.tiendagym.repositories.ProductoRepository;
import com.example.tiendagym.repositories.ProductoVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductoVentaController {

    @Autowired
    ProductoVentaRepository productoVentaRepository;

    @GetMapping("/productoVenta/")
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(productoVentaRepository.findAll(), HttpStatus.OK);
    }


    @GetMapping("/productoVenta/{id}/")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productoVentaRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/productoVenta/create/")
    public ResponseEntity<Object> create(@RequestBody ProductoVenta productoVenta) {
        productoVentaRepository.save(productoVenta);
        return new ResponseEntity<>(productoVenta, HttpStatus.OK);
    }

    @DeleteMapping("/productoVenta/{id}/")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<ProductoVenta> productoVenta = productoVentaRepository.findById(id);
        productoVenta.ifPresent(value -> productoVentaRepository.delete(value));
        return new ResponseEntity<>(productoVenta.isPresent(), HttpStatus.OK);
    }

    @PutMapping("/productoVenta/{id}/")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody ProductoVenta productoVenta) {
        Optional<ProductoVenta> oldPrisoner = productoVentaRepository.findById(id);
        if(oldPrisoner.isPresent()) {
            productoVenta.setId(id);
            productoVentaRepository.save(productoVenta);
            return new ResponseEntity<>(productoVenta, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

}
