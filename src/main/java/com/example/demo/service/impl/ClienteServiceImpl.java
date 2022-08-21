package com.example.demo.service.impl;

import com.example.demo.builder.ClienteBuilder;
import com.example.demo.dao.ClienteRepository;
import com.example.demo.handle.ApiException;
import com.example.demo.model.entity.Cliente;
import com.example.demo.model.request.ClienteRequest;
import com.example.demo.model.response.ClienteResponse;
import com.example.demo.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteResponse buscarPorDni(Long dni) {
        Cliente cliente = clienteRepository.findById(dni).orElse(null);
        if (cliente != null) {
            return ClienteBuilder.entityToResponse(cliente);
        }
        return null;
    }

    @Override
    public List<ClienteResponse> buscarTodos() {
        List<Cliente> listaClientesEntities = clienteRepository.findAll();
        List<ClienteResponse> listaClienteResponse = ClienteBuilder.entityToResponseList(listaClientesEntities);
        return listaClienteResponse;
    }

    @Override
    public ClienteResponse crear(ClienteRequest cliente) throws ApiException {
        try {
            if (buscarPorDni(cliente.getDni()) == null) {
                Cliente clienteAGuardar = ClienteBuilder.requestToEntity(cliente);
                Cliente entity = clienteRepository.save(clienteAGuardar);
                ClienteResponse clienteAResponder = ClienteBuilder.entityToResponse(entity);
                return clienteAResponder;
            } else {
                throw new ApiException("Cliente ya existe");
            }
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    @Override
    public ClienteResponse actualizar(ClienteRequest cliente) throws ApiException {
        try {
            if (buscarPorDni(cliente.getDni()) != null) {
                Cliente entity = clienteRepository.save(ClienteBuilder.requestToEntity(cliente));
                return ClienteBuilder.entityToResponse(entity);
            } else {
                throw new ApiException("Cliente no existe");
            }
        } catch (Exception e) {
            throw new ApiException(e.getMessage());
        }
    }

    @Override
    public void eliminar(ClienteRequest cliente) throws ApiException {
        if (buscarPorDni(cliente.getDni()) != null) {
            Cliente clienteAEliminar = ClienteBuilder.requestToEntity(cliente);
            clienteRepository.delete(clienteAEliminar);
        } else {
            throw new ApiException("Cliente no existe");
        }
    }

    @Override
    public void eliminarPorDni(Long dni) {
        clienteRepository.deleteById(dni);
    }
}
