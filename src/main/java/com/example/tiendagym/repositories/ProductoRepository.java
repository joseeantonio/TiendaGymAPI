package com.example.tiendagym.repositories;

import com.example.tiendagym.models.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto,Long> {}
