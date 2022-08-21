package com.example.demo.builder;

import com.example.demo.model.entity.Factura;
import com.example.demo.model.entity.Inventario;
import com.example.demo.model.request.FacturaRequest;
import com.example.demo.model.response.FacturaResponse;
import com.example.demo.model.response.ItemResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FacturaBuilder {

    public static FacturaResponse entityToResponse(Factura factura) {
        return FacturaResponse.builder()
                .comprobanteId(factura.getFacturaid().longValue())
                .fecha(factura.getFecha())
                .build();
    }

    private static Set<ItemResponse> itemsEntityToResponse(Set<Inventario> item) {
        return item.stream().map(FacturaBuilder::itemEntityToResponse).collect(Collectors.toSet());
    }

    private static ItemResponse itemEntityToResponse(Inventario item) {
        return ItemResponse.builder()
                .producto(ProductoBuilder.entityToResponse(item.getProducto()))
                .build();
    }

}
