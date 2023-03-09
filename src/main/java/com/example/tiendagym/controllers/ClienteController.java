package com.example.tiendagym.controllers;

import com.example.tiendagym.models.Cliente;
import com.example.tiendagym.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ClienteController {


    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/clientes/")
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(clienteRepository.findAll(), HttpStatus.OK);
    }


    @GetMapping("/clientes/{id}/")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clienteRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/clientes/create/")
    public ResponseEntity<Object> create(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }


    @DeleteMapping("/clientes/{id}/")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        cliente.ifPresent(value -> clienteRepository.delete(value));
        return new ResponseEntity<>(cliente.isPresent(), HttpStatus.OK);
    }


    @PutMapping("/clientes/{id}/")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> oldPrisoner = clienteRepository.findById(id);
        if(oldPrisoner.isPresent()) {
            cliente.setId(id);
            clienteRepository.save(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

}
