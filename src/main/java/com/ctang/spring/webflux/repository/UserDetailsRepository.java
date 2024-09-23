package com.ctang.spring.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.ctang.spring.webflux.entity.MyUserDetails;

import reactor.core.publisher.Mono;

@Repository
public interface UserDetailsRepository extends ReactiveCrudRepository<MyUserDetails, Integer>{
        
    Mono<MyUserDetails> findByUsername(String usuario);

}
