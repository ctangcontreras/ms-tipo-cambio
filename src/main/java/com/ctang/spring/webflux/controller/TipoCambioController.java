package com.ctang.spring.webflux.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ctang.spring.webflux.model.CambioRequest;
import com.ctang.spring.webflux.model.HttpResult;
import com.ctang.spring.webflux.service.TipoCambioService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tipoCambio")
@CrossOrigin(origins = "*")
public class TipoCambioController {
    
    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    TipoCambioService tipoCambioService;

 

    @RequestMapping(value = "/{tipo}", method = RequestMethod.GET)
    public Mono<HttpResult> getCliente(@PathVariable String tipo ) {
        return tipoCambioService.getTipoCambio(tipo).map(
            e-> {
                HttpResult result = new HttpResult();
                result.setCode(1);
                result.setData(e);
                result.setMsg("ok");
                return result;
            }
        ).onErrorResume(e -> {
            log.error(e.getMessage(), e);
            return Mono.just(new HttpResult(500, "error",e));

        });
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public Mono<HttpResult> convertir(@RequestBody CambioRequest cambioRequest) {
        return tipoCambioService.convert(cambioRequest).map(
            e-> {
                HttpResult result = new HttpResult();
                result.setCode(1);
                result.setData(e);
                result.setMsg("ok");
                return result;
            }
        ).onErrorResume(e -> {
            log.error(e.getMessage(), e);
            return Mono.just(new HttpResult(500, "error",e));

        });
    }
  

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Mono<HttpResult> findAll() {
            return tipoCambioService.findAll().collectList()
            .flatMap(e->
                Mono.just(new HttpResult(200, "ok",e))

            ).onErrorResume(e -> {
                log.error(e.getMessage(), e);
                return Mono.just(new HttpResult(500, "error",e));

            }); 
    }
}
