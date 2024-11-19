package com.ws.cache.service;

import com.ws.cache.entity.ProductDto;

import java.util.Optional;

public interface ProductCacheService {


    public Optional<Boolean> isDuplicate(ProductDto product);


    public void saveProduct(ProductDto product);

}
