package com.example.demo.service.impl;

import com.example.demo.builder.FacturaBuilder;
import com.example.demo.dao.ClienteRepository;
import com.example.demo.dao.FacturaRepository;
import com.example.demo.dao.InventarioRepository;
import com.example.demo.dao.ProductoRepository;
import com.example.demo.handle.ApiException;
import com.example.demo.model.entity.Factura;
import com.example.demo.model.entity.Inventario;
import com.example.demo.model.entity.Producto;
import com.example.demo.model.request.ClienteRequest;
import com.example.demo.model.request.FacturaRequest;
import com.example.demo.model.request.InventarioRequest;
import com.example.demo.model.response.FacturaResponse;
import com.example.demo.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;

    private final InventarioRepository itemRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;


    @Override
    public FacturaResponse crear(FacturaRequest comprobante) throws ApiException {


        if (isValidRequest(comprobante)) {

            Factura entityAGuardar = crearComprobanteConProductos(comprobante);

            var entitySaved = facturaRepository.save(entityAGuardar);

            var items = itemRepository.saveAll(guardarItems(entitySaved.getFacturaid(), entityAGuardar.getItem()));

            reducirStockDeProductos(comprobante);

            return FacturaBuilder.entityToResponse(entitySaved);
        } else {
            throw new ApiException("Ocurrió un error inesperado");
        }
    }

    private List<Inventario> guardarItems(Integer facturaid, Set<Inventario> item) {
        return item.stream().peek(itemEntity -> itemEntity.setFacturaid(facturaid)).collect(Collectors.toList());
    }

    private void reducirStockDeProductos(FacturaRequest factura) {

        factura.getItems().forEach(item -> {

            var productoDB = this.productoRepository.findByCodigo(item.getProducto().getCodigo()).get();

            Integer nuevoStock = productoDB.getStock() - item.getCantidad();

            productoDB.setProductoId((int) nuevoStock.longValue());

            productoRepository.save(productoDB);
        });
    }

    private Factura crearComprobanteConProductos(FacturaRequest comprobante) {
        return Factura.builder()
                .cliente(clienteRepository.findById(comprobante.getCliente().getDni()).get())
                .fecha(obtenerFechaActual())
                .item(obtenerItems(comprobante.getItems()))
                .total(calcularPrecioTotal(comprobante.getItems()))
                .build();
    }

    private BigDecimal calcularPrecioTotal(Set<InventarioRequest> items) {
        return items.stream()
                .flatMap(item -> {
                    var producto = productoRepository.findByCodigo(item.getProducto().getCodigo());
                    return producto.stream().map(current -> current.getPrecio().multiply(BigDecimal.valueOf(item.getCantidad().longValue())));
                }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Set<Inventario> obtenerItems(Set<InventarioRequest> items) {
        Set<Inventario> Inventario = new HashSet<>();
        items.forEach(item -> Inventario.add(obtenerItem(item)));
        return Inventario;
    }

    private Inventario obtenerItem(InventarioRequest item) {
        var producto = productoRepository.findByCodigo(item.getProducto().getCodigo());
        return Inventario.builder()
                .cantidad(item.getCantidad())
                .descripcion(producto.get().getDescripcion())
                .precio(producto.get().getPrecio())
                .producto(producto.get())
                .build();
    }

    private Date obtenerFechaActual() {
        return new Date();
    }

    private boolean isValidRequest(FacturaRequest comprobante) {
        return isValidCliente(comprobante.getCliente())
                && isValidItems(comprobante.getItems());
    }

    private boolean isValidItems(Set<InventarioRequest> items) {
        return existsProducts(items) && existsStock(items);
    }

    private boolean existsStock(Set<InventarioRequest> items) {
        for (InventarioRequest item : items) {
            var produdcto = existsProduct(item);
            if (existsProduct(item).isPresent()) {
                return true;
            }
            if (item.getCantidad() < produdcto.get().getStock()) {
                return false;
            }
        }
        return false;
    }

    private boolean existsProducts(Set<InventarioRequest> items) {
        for (InventarioRequest item : items) {
            return existsProduct(item).isPresent();
        }
        return true;
    }

    private Optional<Producto> existsProduct(InventarioRequest item) {
        var codigoProducto = item.getProducto().getCodigo();
        return this.productoRepository.findByCodigo(codigoProducto);
    }

    private boolean isValidCliente(ClienteRequest cliente) {
        return clienteRepository.existsById(cliente.getDni());
    }

}
