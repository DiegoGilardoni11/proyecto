package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "FACTURA")
@Transactional
public class Factura {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "FACTURAID")
    private Integer facturaid;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name="CLIENTEID")
    private Cliente cliente;

    @Transient
    private Set<Inventario> item;

}
