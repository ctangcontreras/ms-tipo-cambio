package com.ctang.spring.webflux.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CAMBIO",schema = "FINANCIA")
@Setter
@Getter
@Builder
public class Cambio {

    @Id
    @Column("ID_CAMBIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCambio;

    @Column("MONEDA_ORIGEN")
    private String monedaOrigen;

    @Column("MONEDA_DESTINO")
    private String monedaDestino;

    @Column("MONTO_ORIGEN")
    private BigDecimal montoOrigen;
    
    @Column("MONTO_DESTINO")
    private BigDecimal montoDestino;
}
