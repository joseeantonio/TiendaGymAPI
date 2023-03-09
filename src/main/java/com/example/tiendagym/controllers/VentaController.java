package com.example.tiendagym.controllers;

import com.example.tiendagym.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
