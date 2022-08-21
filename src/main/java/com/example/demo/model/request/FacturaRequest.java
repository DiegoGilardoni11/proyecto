package com.example.demo.model.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Validated
public class FacturaRequest {

    private ClienteRequest cliente;
    private Set<InventarioRequest> items;

}
