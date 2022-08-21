package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "INVENTARIO")
@Transactional
public class Inventario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ITEMID")
    private Integer itemid;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "PRECIO")
    private BigDecimal precio;


    @Column(name = "FACTURAID")
    private Integer facturaid;

    @ManyToOne
    @JoinColumn(name="PRODUCTOID")
    private Producto producto;

}
