package com.company.domain;

import jdk.nashorn.internal.ir.debug.JSONWriter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class Order
{
    private int orderNr;
    private boolean isStudentOrder;

    private ArrayList<MovieTicket> tickets;

    public Order(int orderNr, boolean isStudentOrder)
    {
        this.orderNr = orderNr;
        this.isStudentOrder = isStudentOrder;

        tickets = new ArrayList<MovieTicket>();
    }

    public int getOrderNr()
    {
        return orderNr;
    }

    public void addSeatReservation(MovieTicket ticket)
    {
        tickets.add(ticket);
    }

    // 1.1
    public double calculatePrice()
    {
        ArrayList<Double> pricesToAddTemp = new ArrayList<>();
        boolean mondayToThursday = false;

        // Iterate over every ticket
        for(MovieTicket ticket : tickets){

            // Price and datetime of MovieScreening
            double ticketPrice = ticket.getMovieScreening().getPricePerSeat();
            LocalDateTime ticketDateTime = ticket.getMovieScreening().getDateAndTime();
            DayOfWeek dayOfWeekDay = ticketDateTime.getDayOfWeek();
            int dayOfWeek = dayOfWeekDay.getValue();

            // Set order mon-thurs
            if (dayOfWeek >= 1 && dayOfWeek <= 4){
                mondayToThursday = true;
            }

            // Add price to ticket if Premium
            if(ticket.isPremiumTicket()){
                if(this.isStudentOrder){ // If the order is marked as Student order, add 2. Else add 3.
                    ticketPrice = ticketPrice + 2.0;
                } else {
                    ticketPrice = ticketPrice + 3.0;
                }
            }

            // 10% discount on order if
            // - 6 tickets or more on order
            // & weekend day
            // & student order
            if(tickets.size() >= 6
                    && (dayOfWeek >= 6 && dayOfWeek <= 7)
                    && this.isStudentOrder){
                ticketPrice = ticketPrice * 0.9;
            }

            // Add the price to total pricesToAdd array
            pricesToAddTemp.add(ticketPrice);
        }

        // Second ticket free if:
        // - order is marked as Student order
        // & if day == ma-do
        if(this.isStudentOrder || (mondayToThursday)){
            int a = 0;
            ArrayList<Double> pricesToAddFinal = new ArrayList<>();

            for(Double priceToAdd : pricesToAddTemp){
                if(a != 0){
                    pricesToAddFinal.add(0.0); // Make every second ticket cost 0.0 (free)
                    a = 0;
                } else {
                    a = 1;
                    pricesToAddFinal.add(priceToAdd);
                }
            }

            // Final price calculation (with every second ticket free)
            Double finalPrice = 0.0;
            for(Double price : pricesToAddFinal){
                finalPrice = finalPrice + price;
            }
            return finalPrice;
        } else {

            // Final price calculation (with all tickets full price)
            Double finalPrice = 0.0;
            for(Double price : pricesToAddTemp){
                finalPrice = finalPrice + price;
            }
            return finalPrice;
        }
    }

    public void setTickets(ArrayList<MovieTicket> tickets) {
        this.tickets = tickets;
    }

    public void export(TicketExportFormat exportFormat) {
        FileWriter file;
        JSONObject jsonObject = new JSONObject();
        JSONArray ticketArray = new JSONArray();

        if(exportFormat.equals(TicketExportFormat.JSON)) {

            try {
                file = new FileWriter(String.format("Order_%s.json", orderNr));

                for(MovieTicket ticket : tickets) {
                    ticketArray.put("Title");
                    ticketArray.put(ticket.getMovieScreening());
                    ticketArray.put("Premium");
                    ticketArray.put(ticket.isPremiumTicket());
                }

                jsonObject.put("Tickets", ticketArray);
                jsonObject.put("TotalPrice", String.valueOf(calculatePrice()));

                file.write(jsonObject.toString());

                file.flush();
                file.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        if(exportFormat.equals(TicketExportFormat.PLAINTEXT)) {
            try {
                file = new FileWriter(String.format("Order_%s.txt", orderNr));
                for(MovieTicket ticket : tickets) {
                    file.write(ticket.toString() + "\n");
                }
                file.write("\nTotal: €" + String.valueOf(calculatePrice()));

                file.flush();
                file.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
