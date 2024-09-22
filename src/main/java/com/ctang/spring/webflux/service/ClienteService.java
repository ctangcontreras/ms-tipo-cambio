package com.ctang.spring.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctang.spring.webflux.dto.ClienteDto;
import com.ctang.spring.webflux.dto.ProductoDto;
import com.ctang.spring.webflux.entity.Producto;
import com.ctang.spring.webflux.repository.ClienteRepository;
import com.ctang.spring.webflux.repository.ProductoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProductoRepository productoRepository;

    public Mono<ClienteDto> listarClientePorId(Integer idCliente) {
        return clienteRepository.findById(idCliente)
                .map(cliente -> {
                    ClienteDto dto = new ClienteDto();
                    dto.setIdCliente(cliente.getIdCliente());
                    dto.setCodigoUnico(cliente.getCodigoUnico());
                    dto.setNombres(cliente.getNombres());
                    dto.setApellidoPaterno(cliente.getApellidoPaterno());
                    dto.setApellidoMaterno(cliente.getApellidoMaterno());
                    dto.setTipoDocumento(cliente.getTipoDocumento());
                    dto.setNumeroDocumento(cliente.getNumeroDocumento());
                    return dto;
                })
                .defaultIfEmpty(new ClienteDto());
    }

    public Mono<Producto> listarProductoPorId(Integer idProducto){
        return productoRepository.findById(idProducto);
    }

    public Flux<Producto> listarProductosPorIdCliente(Integer idCliente){
        return productoRepository.findByIdCliente(idCliente);
    }

    public Mono<ClienteDto> getInfo(String codigoUnico) {
        return getInfoCliente(codigoUnico)
                .flatMap(clienteDto -> getInfoProductos(clienteDto.getIdCliente())
                        .collectList()
                        .map(productoDtos -> {
                            clienteDto.setListaProductos(productoDtos);
                            return clienteDto;
                        })
                );
    }
 
 
    private Mono<ClienteDto> getInfoCliente(String codigoUnico) {
        return clienteRepository.findByCodigoUnico(codigoUnico)
                .map(cliente -> {
                    ClienteDto dto = new ClienteDto();
                    dto.setIdCliente(cliente.getIdCliente());
                    dto.setCodigoUnico(cliente.getCodigoUnico());
                    dto.setNombres(cliente.getNombres());
                    dto.setApellidoPaterno(cliente.getApellidoPaterno());
                    dto.setApellidoMaterno(cliente.getApellidoMaterno());
                    dto.setTipoDocumento(cliente.getTipoDocumento());
                    dto.setNumeroDocumento(cliente.getNumeroDocumento());
                    return dto;
                })
                .defaultIfEmpty(new ClienteDto());
    }

    private Flux<ProductoDto> getInfoProductos(Integer idCliente) {
        return productoRepository.findByIdCliente(idCliente)
                .map(producto -> {
                    ProductoDto dto = new ProductoDto();
                    dto.setIdProducto(producto.getIdProducto());
                    dto.setIdCliente(producto.getIdCliente());
                    dto.setTipoProducto(producto.getTipoProducto());
                    dto.setNombreProducto(producto.getNombreProducto());
                    dto.setSaldo(producto.getSaldo());
                    return dto;
                });
    }
    
}
