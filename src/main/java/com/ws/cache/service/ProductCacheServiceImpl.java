package com.ws.cache.service;

import com.ws.cache.entity.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;
import java.util.Optional;

@Service
public class ProductCacheServiceImpl implements ProductCacheService {

    private static final String PRODUCT_CACHE_KEY = "product_cache";

    @Autowired
    private Jedis jedis;

    public Optional<Boolean> isDuplicate(ProductDto product) {
        String productKey = String.valueOf(product.hashCode());
        return Optional.of(jedis.sismember(PRODUCT_CACHE_KEY, productKey));
    }

    public void saveProduct(ProductDto product) {
        String productKey = String.valueOf(product.hashCode());
        jedis.sadd(PRODUCT_CACHE_KEY, productKey);
    }
}