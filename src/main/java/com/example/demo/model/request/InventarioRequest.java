package com.example.demo.model.request;

import com.example.demo.model.entity.Factura;
import com.example.demo.model.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InventarioRequest {

    private Integer cantidad;
    private ProductoRequest producto;


}
