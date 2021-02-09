package com.company.domain.exporting;

import com.company.domain.Order;

public interface IExporter {
    void exportOrder(Order order, String fileName);
}
