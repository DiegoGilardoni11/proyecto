package com.example.demo.builder;

import com.example.demo.model.entity.Producto;
import com.example.demo.model.request.ProductoRequest;
import com.example.demo.model.response.ProductoResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ProductoBuilder {

    public static ProductoResponse entityToResponse(Producto producto) {
        return ProductoResponse.builder()
                .productoId(producto.getProductoId().intValue())
                .codigo(producto.getCodigo())
                .precio(producto.getPrecio())
                .cantidad(producto.getStock())
                .descripcion(producto.getDescripcion())
                .build();
    }

    public static List<ProductoResponse> entityToResponseList(List<Producto> entityList) {
        return entityList.stream().map(ProductoBuilder::entityToResponse).collect(Collectors.toList());
    }

    public static Producto requestToEntity(ProductoRequest producto) {
        return Producto.builder()
                .codigo(producto.getCodigo())
                .precio(producto.getPrecio())
                .stock(producto.getCantidad())
                .descripcion(producto.getDescripcion())
                .build();
    }
}
