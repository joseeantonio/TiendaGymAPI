package com.example.tiendagym.controllers;

import com.example.tiendagym.models.Producto;
import com.example.tiendagym.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductoController {

    @Autowired
    ProductoRepository productoRepository;

    @GetMapping("/maquinas/")
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(productoRepository.findAll(), HttpStatus.OK);
    }


    @GetMapping("/maquinas/{id}/")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productoRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/maquinas/create/")
    public ResponseEntity<Object> create(@RequestBody Producto producto) {
        productoRepository.save(producto);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }


    @DeleteMapping("/maquinas/{id}/")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        producto.ifPresent(value -> productoRepository.delete(value));
        return new ResponseEntity<>(producto.isPresent(), HttpStatus.OK);
    }


    @PutMapping("/maquinas/{id}/")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Producto producto) {
        Optional<Producto> oldPrisoner = productoRepository.findById(id);
        if(oldPrisoner.isPresent()) {
            producto.setId(id);
            productoRepository.save(producto);
            return new ResponseEntity<>(producto, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
