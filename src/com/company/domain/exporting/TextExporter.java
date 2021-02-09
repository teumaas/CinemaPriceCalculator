package com.company.domain.exporting;

import com.company.domain.MovieTicket;
import com.company.domain.Order;

import java.io.FileWriter;
import java.io.IOException;

public class TextExporter implements IExporter {
    @Override
    public void exportOrder(Order order, String filename) {
        System.out.println("Exporting order in TXT format to " + filename + ".txt");

        FileWriter file;

        try {
            file = new FileWriter(filename + ".txt");
            for(MovieTicket ticket : order.getTickets()) {
                file.write(ticket.toString() + "\n");
            }
            file.write("\nTotal: €" + String.valueOf(order.calculatePrice()));

            file.flush();
            file.close();
        } catch (IOException err) {
            System.out.println("Error whilst trying to export order to TXT-file:");
            System.out.println(err.toString());
        }
    }
}
