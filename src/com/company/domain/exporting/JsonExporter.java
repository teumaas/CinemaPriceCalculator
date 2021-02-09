package com.company.domain.exporting;

import com.company.domain.MovieTicket;
import com.company.domain.Order;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonExporter implements IExporter {
    @Override
    public void exportOrder(Order order, String fileName) {
        System.out.println("Exporting order in JSON format to " + fileName + ".json");

        FileWriter file;
        JSONObject jsonObject = new JSONObject();
        JSONArray ticketArray = new JSONArray();

        try {
            file = new FileWriter(fileName + ".json");

            for(MovieTicket ticket : order.getTickets()) {
                ticketArray.put("Title");
                ticketArray.put(ticket.getMovieScreening());
                ticketArray.put("Premium");
                ticketArray.put(ticket.isPremiumTicket());
            }

            jsonObject.put("Tickets", ticketArray);
            jsonObject.put("TotalPrice", String.valueOf(order.calculatePrice()));

            file.write(jsonObject.toString());

            file.flush();
            file.close();
        } catch (IOException err) {
            System.out.println("Error whilst trying to export order to JSON-file:");
            System.out.println(err.toString());
        }

    }
}
