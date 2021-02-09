package com.company.domain.exporting;


import com.company.domain.Order;

// Acts as a context for exporting
public class Exporter {
    private IExporter exportHandler;

    public Exporter(IExporter exportMethod) {
        this.exportHandler = exportMethod;

    }

    public void exportFile(Order order, String fileName) {
        this.exportHandler.exportOrder(order, fileName);
    }
}
