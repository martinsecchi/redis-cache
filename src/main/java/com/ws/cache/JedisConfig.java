package com.ws.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.*;

@Configuration
public class JedisConfig {

    @Bean
    public Jedis jedis() {
        return new Jedis("localhost", 6379); // Cambia la configuración según tu entorno
    }
}