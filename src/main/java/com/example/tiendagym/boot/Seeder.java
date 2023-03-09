package com.example.tiendagym.boot;

import com.example.tiendagym.models.Cliente;
import com.example.tiendagym.models.Producto;
import com.example.tiendagym.models.ProductoVenta;
import com.example.tiendagym.models.Venta;
import com.example.tiendagym.repositories.ClienteRepository;
import com.example.tiendagym.repositories.ProductoRepository;
import com.example.tiendagym.repositories.ProductoVentaRepository;
import com.example.tiendagym.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {

    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    VentaRepository ventaRepository;
    @Autowired
    ProductoVentaRepository productoVentaRepository;

    @Override
    public void run(String... args){

        Producto producto1 = new Producto(200,"prensa","descripciooooooooooon1","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png");
        Producto producto2 = new Producto(300,"press banca","descripciooooooooooon2","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png");

        productoRepository.save(producto1);
        productoRepository.save(producto2);


        Cliente cliente1 = new Cliente("a@a.com","password","pepito02");
        Cliente cliente2 = new Cliente("i@a.com","password","pepita02");

        clienteRepository.save(cliente1);
        clienteRepository.save(cliente2);



        Venta venta1 = new Venta("20-10-2025", "Desde un ordenador",cliente1);
        ventaRepository.save(venta1);

        ProductoVenta productoVenta1 = new ProductoVenta(venta1,producto1, 2);
        productoVentaRepository.save(productoVenta1);

    }
}
