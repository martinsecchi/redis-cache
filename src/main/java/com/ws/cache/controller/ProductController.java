package com.ws.cache.controller;

import com.ws.cache.entity.CheckResponse;
import com.ws.cache.entity.ProductDto;
import com.ws.cache.service.ProductCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/check/products")
public class ProductController {

    @Autowired
    private ProductCacheService productCacheService;

    @PostMapping
    public ResponseEntity<CheckResponse<String>> checkProduct(@RequestBody ProductDto product) {
        return productCacheService.isDuplicate(product)
                .map(isDuplicate -> {
                    if (isDuplicate) {
                        return ResponseEntity.ok(new CheckResponse<String>(false, "El producto ya existe (duplicado).", null));
                    } else {
                        productCacheService.saveProduct(product);
                        return ResponseEntity.status(HttpStatus.CREATED)
                                .body(new CheckResponse<String>(true, "Producto registrado exitosamente.", null));
                    }
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
