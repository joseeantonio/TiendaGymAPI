package com.example.tiendagym.controllers;

import com.example.tiendagym.repositories.ProductoRepository;
import com.example.tiendagym.repositories.ProductoVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoVentaController {

    @Autowired
    ProductoVentaRepository productoVentaRepository;

    @GetMapping("/productoVenta/")
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(productoVentaRepository.findAll(), HttpStatus.OK);
    }
}
