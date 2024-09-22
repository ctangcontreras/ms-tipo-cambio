package com.ctang.spring.webflux.client;
 
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ctang.spring.webflux.model.TipoCambio;

@FeignClient(name = "tipoCambioClient", url = "https://open.er-api.com/v6")
@Component
public interface TipoCambioClient {


    @GetMapping("/latest/{tipo}")
    TipoCambio getTipoCambio(@PathVariable String tipo);
}

