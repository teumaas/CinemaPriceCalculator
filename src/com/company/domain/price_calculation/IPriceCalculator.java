package com.company.domain.price_calculation;

import com.company.domain.Order;

public interface IPriceCalculator {
    double calculatePrice(Order order);
}
