package com.example.tiendagym.security;

import com.example.tiendagym.models.Cliente;
import com.example.tiendagym.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Cliente cliente = clienteRepository.findByUsername(username);
        if (cliente == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(cliente);
    }
}