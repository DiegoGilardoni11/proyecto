package com.example.demo.service.impl;

import com.example.demo.builder.ProductoBuilder;
import com.example.demo.dao.ProductoRepository;
import com.example.demo.handle.ApiException;
import com.example.demo.model.entity.Producto;
import com.example.demo.model.request.ProductoRequest;
import com.example.demo.model.response.ProductoResponse;
import com.example.demo.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public ProductoResponse buscarPorId(Integer id) {
        Producto producto = productoRepository.findById(Long.valueOf(id)).orElse(null);
        return ProductoBuilder.entityToResponse(producto);
    }

    @Override
    public ProductoResponse buscarPorCodigo(Integer codigo) {
        var producto = productoRepository.findByCodigo(codigo);
        if (producto.isPresent()) {
            return ProductoBuilder.entityToResponse(producto.get());
        }
        return null;
    }

    @Override
    public List<ProductoResponse> buscarTodos() {
        List<Producto> listaProductosEntities = productoRepository.findAll();
        return ProductoBuilder.entityToResponseList(listaProductosEntities);
    }

    @Override
    public ProductoResponse crear(ProductoRequest producto) throws ApiException {
        try {
            if (buscarPorCodigo(producto.getCodigo()) == null) {
                Producto productoAGuardar = ProductoBuilder.requestToEntity(producto);
                Producto entity = productoRepository.save(productoAGuardar);
                return ProductoBuilder.entityToResponse(entity);
            } else {
                throw new ApiException("Producto ya existe");
            }
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    @Override
    public ProductoResponse actualizar(ProductoRequest producto) throws ApiException {
        try {
            if (buscarPorCodigo(producto.getCodigo()) == null) {
                Producto entity = productoRepository.save(ProductoBuilder.requestToEntity(producto));
                return ProductoBuilder.entityToResponse(entity);
            } else {
                throw new ApiException("Producto no existe");
            }
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    @Override
    public void eliminar(ProductoRequest producto) throws ApiException {
        if (buscarPorCodigo(producto.getCodigo()) == null) {
            Producto productoAEliminar = ProductoBuilder.requestToEntity(producto);
            productoRepository.delete(productoAEliminar);
        } else {
            throw new ApiException("Producto no existe");
        }
    }

    @Override
    public void eliminarPorId(Long id) {
        productoRepository.deleteById(id);
    }
}
