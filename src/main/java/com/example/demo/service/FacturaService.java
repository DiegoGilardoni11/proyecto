package com.example.demo.service;

import com.example.demo.handle.ApiException;
import com.example.demo.model.request.FacturaRequest;
import com.example.demo.model.response.FacturaResponse;

import java.util.List;

public interface FacturaService {

    FacturaResponse crear(FacturaRequest request) throws ApiException;
}
