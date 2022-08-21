package com.example.demo.controller;

import com.example.demo.handle.ApiException;
import com.example.demo.model.request.FacturaRequest;
import com.example.demo.model.response.FacturaResponse;
import com.example.demo.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/factura")
public class FacturaController {

    private final FacturaService service;

    @PostMapping()
    public ResponseEntity<FacturaResponse> crearFactura(@RequestBody FacturaRequest request) throws ApiException {
        return ResponseEntity.ok(service.crear(request));
    }

}
