package com.ctang.spring.webflux.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.ctang.spring.webflux.entity.Producto;

import reactor.core.publisher.Flux;

@Repository
public interface ProductoRepository extends R2dbcRepository<Producto, Integer>{
    
    Flux<Producto> findByIdCliente(Integer idCliente);
}
