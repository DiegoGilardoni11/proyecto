package com.example.demo.service;

import com.example.demo.handle.ApiException;
import com.example.demo.model.request.ProductoRequest;
import com.example.demo.model.response.ProductoResponse;

import java.util.List;

public interface ProductoService {

    ProductoResponse buscarPorId(Integer id);
    ProductoResponse buscarPorCodigo(Integer codigo);
    List<ProductoResponse> buscarTodos();
    ProductoResponse crear(ProductoRequest producto) throws ApiException;
    ProductoResponse actualizar(ProductoRequest producto) throws ApiException;
    void eliminar(ProductoRequest producto) throws ApiException;
    void eliminarPorId(Long id);
}
