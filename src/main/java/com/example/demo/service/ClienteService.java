package com.example.demo.service;

import com.example.demo.handle.ApiException;
import com.example.demo.model.request.ClienteRequest;
import com.example.demo.model.response.ClienteResponse;

import java.util.List;

public interface ClienteService {

    ClienteResponse buscarPorDni(Long dni) throws ApiException;
    List<ClienteResponse> buscarTodos();
    ClienteResponse crear(ClienteRequest cliente) throws ApiException;
    ClienteResponse actualizar(ClienteRequest cliente) throws ApiException;
    void eliminar(ClienteRequest cliente) throws ApiException;
    void eliminarPorDni(Long dni);
}
