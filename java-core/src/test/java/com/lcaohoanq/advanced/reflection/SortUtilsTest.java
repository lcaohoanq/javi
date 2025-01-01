package com.lcaohoanq.advanced.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortUtilsTest {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Product {
        private Long id;
        private String name;
        private Double price;
    }
    
    List<Product>products;
    
    @BeforeEach
    void setUp() {
        products = new ArrayList<>(
            List.of(
                Product.builder()
                    .id(1L)
                    .name("Product 1")
                    .price(100.0)
                    .build(),
                Product.builder()
                    .id(2L)
                    .name("Product 2")
                    .price(50.0)
                    .build(),
                Product.builder()
                    .id(3L)
                    .name("Product 3")
                    .price(150.0)
                    .build()
            )
        );
    }

    @Test
    void sortPriceASC() {
        // When
        SortUtils.sort(products, "price", SortOrder.ASC, Product.class);

        // Then
        assertEquals(50.0, products.get(0).getPrice());
        assertEquals(100.0, products.get(1).getPrice());
        assertEquals(150.0, products.get(2).getPrice());
    }
    
    @Test
    void sortPriceDESC() {
        // When
        SortUtils.sort(products, "price", SortOrder.DESC, Product.class);

        // Then
        assertEquals(150.0, products.get(0).getPrice());
        assertEquals(100.0, products.get(1).getPrice());
        assertEquals(50.0, products.get(2).getPrice());
    }
}