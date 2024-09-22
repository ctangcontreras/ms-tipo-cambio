package com.ctang.spring.webflux.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.ctang.spring.webflux.entity.Empleado;

import reactor.core.publisher.Flux;

@Repository
public interface EmpleadoRepository extends R2dbcRepository<Empleado, Integer> {
    
    @Query("SELECT * FROM Empleado e WHERE estado = 1")
    Flux<Empleado> listarEmpleadosActivos();
}
