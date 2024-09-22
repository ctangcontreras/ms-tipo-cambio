package com.ctang.spring.webflux.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.ctang.spring.webflux.entity.Usuario;

import reactor.core.publisher.Mono;

@Repository
public interface UsuarioRepository extends R2dbcRepository<Usuario, Integer>{
        
        Mono<Usuario> findByUsuario(String usuario);

}
