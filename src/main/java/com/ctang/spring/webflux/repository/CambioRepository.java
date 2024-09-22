package com.ctang.spring.webflux.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.ctang.spring.webflux.entity.Cambio;

@Repository
public interface CambioRepository extends R2dbcRepository<Cambio, Integer> {
     
}
