package com.anjali.smartproductcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SmartProductCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartProductCatalogApplication.class, args);
    }
}