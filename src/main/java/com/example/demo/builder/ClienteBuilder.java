package  com.example.demo.builder;

import com.example.demo.model.entity.Cliente;
import com.example.demo.model.request.ClienteRequest;
import com.example.demo.model.response.ClienteResponse;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteBuilder {

    public static ClienteResponse entityToResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .dni(cliente.getDni())
                .apellido(cliente.getApellido())
                .nombre(cliente.getNombre())
                .build();
    }

    public static List<ClienteResponse> entityToResponseList(List<Cliente> entityList) {
        return entityList.stream()
                .map(ClienteBuilder::entityToResponse)
                .collect(Collectors.toList());
    }

    public static Cliente requestToEntity(ClienteRequest cliente) {
        return Cliente.builder()
                .dni(cliente.getDni())
                .apellido(cliente.getApellido())
                .nombre(cliente.getNombre())
                .build();
    }
}
