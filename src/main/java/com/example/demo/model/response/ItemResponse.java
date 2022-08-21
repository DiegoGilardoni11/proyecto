package com.example.demo.model.response;

import com.example.demo.model.request.ProductoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ItemResponse {

    private ProductoResponse producto;

}
