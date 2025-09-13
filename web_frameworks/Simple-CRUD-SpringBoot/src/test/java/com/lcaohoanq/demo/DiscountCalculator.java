package com.lcaohoanq.demo;

public class DiscountCalculator {

    public double calculateDiscount(double totalAmount) {

        if (totalAmount < 100) {
            return 0;
        } else if (totalAmount < 500) {
            return totalAmount * 0.10;
        } else {
            return totalAmount * 0.20;
        }

    }

}
