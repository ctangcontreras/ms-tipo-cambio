package com.ctang.spring.webflux.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctang.spring.webflux.client.TipoCambioClient;
import com.ctang.spring.webflux.entity.Cambio;
import com.ctang.spring.webflux.model.CambioRequest;
import com.ctang.spring.webflux.model.TipoCambio;
import com.ctang.spring.webflux.repository.CambioRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TipoCambioService {
    
    @Autowired
    private TipoCambioClient tipoCambioClient;   

    @Autowired
    private CambioRepository cambioRepository;
 
    public Mono<TipoCambio> getTipoCambio(String tipo) {
        return Mono.just(tipoCambioClient.getTipoCambio(tipo));
    }
 
    public Mono<Object> convert(CambioRequest cambioRequest) {

        return  getTipoCambio(cambioRequest.getMonedaOrigen()).flatMap(e->{
                if (e.getResult().equals("error")) {                    
                    return Mono.error(new RuntimeException("Error en el tipo de cambio origen"));
                }else{
                    if (e.getRates().get(cambioRequest.getMonedaDestino())==null) {
                        return Mono.error(new RuntimeException("Error en el tipo de cambio destino"));
                    }
                    Double tipo = (Double) e.getRates().get(cambioRequest.getMonedaDestino());       
                    cambioRequest.setMontoDestino(cambioRequest.getMontoOrigen().multiply(BigDecimal.valueOf(tipo)));                
                    return registrar(cambioRequest);
                }
                
            
        });
    }
    
    
    public Mono<Cambio> registrar(CambioRequest cambioRequest) {
        Cambio c = Cambio.builder()
        .monedaDestino(cambioRequest.getMonedaDestino())
        .monedaOrigen(cambioRequest.getMonedaOrigen())
        .montoOrigen(cambioRequest.getMontoOrigen())
        .montoDestino(cambioRequest.getMontoDestino())
        .build(); 
        return  cambioRepository.save(c)
        .doOnError(error -> {
            // Log error here
            System.err.println("Error saving Cambio: " + error.getMessage());
        });
            
    }
    

    public Flux<Cambio> findAll() {
        return cambioRepository.findAll();
    }
}
