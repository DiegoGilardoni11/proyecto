package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "PRODUCTO")
public class Producto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PRODUCTOID")
    private Integer productoId;

    @Column(name = "CODIGO")
    private Integer codigo;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "STOCK")
    private Integer stock;

    @Column(name = "PRECIO")
    private BigDecimal precio;

}
