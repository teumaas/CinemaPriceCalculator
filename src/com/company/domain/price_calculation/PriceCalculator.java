package com.company.domain.price_calculation;

import com.company.domain.Order;


// This class serves as a context for the price calculation
public class PriceCalculator {

    private IPriceCalculator priceCalculatorHandler;

    public PriceCalculator(IPriceCalculator priceCalculator) {
        this.priceCalculatorHandler = priceCalculator;
    }

    public double calculatePrice(Order order) {
        return priceCalculatorHandler.calculatePrice(order);
    }

}
