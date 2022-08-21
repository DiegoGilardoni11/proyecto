package com.example.demo.controller;

import com.example.demo.handle.ApiException;
import com.example.demo.model.request.ProductoRequest;
import com.example.demo.model.response.ProductoResponse;
import com.example.demo.service.ClienteService;
import com.example.demo.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    @GetMapping()
    public ResponseEntity<List<ProductoResponse>> obtenerProductos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(Math.toIntExact(id)));
    }
    @PostMapping("/actualizar")
    public ResponseEntity<ProductoResponse> actualizar(@Valid @RequestBody ProductoRequest cliente) throws ApiException {
        return ResponseEntity.ok(service.actualizar(cliente));
    }

    @PostMapping("/crear")
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest producto) throws ApiException {
        return ResponseEntity.ok(service.crear(producto));
    }

    @PostMapping("/eliminar")
    public void eliminar(@Valid @RequestBody ProductoRequest producto) throws ApiException {
        service.eliminar(producto);
    }

    @PostMapping("/eliminar/{id}")
    public void eliminarPorId(@PathVariable Long id) {
        service.eliminarPorId(id);
    }

}
