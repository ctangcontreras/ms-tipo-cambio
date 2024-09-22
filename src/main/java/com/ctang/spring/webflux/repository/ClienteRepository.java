package com.ctang.spring.webflux.repository;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.ctang.spring.webflux.entity.Cliente;

import reactor.core.publisher.Mono;


@Repository
public interface ClienteRepository extends R2dbcRepository<Cliente, Integer> {
    Mono<Cliente> findByCodigoUnico(String codigoUnico);
}


