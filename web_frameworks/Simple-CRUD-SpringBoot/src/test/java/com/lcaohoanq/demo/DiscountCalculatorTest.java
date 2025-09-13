package com.lcaohoanq.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DiscountCalculatorTest {

    @Test
    public void testNoDiscount(){
        var discountObj = new DiscountCalculator();

        //<100 => 0
        double total = discountObj.calculateDiscount(50);
        assertEquals(0, total);
    }

    @Test
    public void test10Discount(){
        var discountObj = new DiscountCalculator();

        //<100 => 0
        double total = discountObj.calculateDiscount(150);
        assertEquals(15, total);
    }

    @Test
    public void test20Discount(){
        var discountObj = new DiscountCalculator();

        //<100 => 0
        double total = discountObj.calculateDiscount(600);
        assertEquals(120, total);
    }

}
