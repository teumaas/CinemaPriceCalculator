package domain;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.util.ArrayList;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

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

        // Day of the week
        Calendar c = Calendar.getInstance();
        java.util.Date date = new java.util.Date();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        // Iterate over every ticket
        for(MovieTicket ticket : tickets){
            double ticketPrice = 11.0; // Assumption: ticket price 11 euro Pathé (outrageous)

            // Add price to ticket if Premium
            if(ticket.isPremiumTicket()){
                if(this.isStudentOrder){ // if the order is marked as Student order, add 2. Else add 3.
                    ticketPrice = ticketPrice + 2.0;
                } else {
                    ticketPrice = ticketPrice + 3.0;
                }
            }

            // 10% discount on order if 6 tickets or more on order & weekend day & student order
            if(tickets.size() >= 6 && (dayOfWeek >= 6 && dayOfWeek <= 7) && this.isStudentOrder){
                ticketPrice = ticketPrice * 0.9;
            }

            // Add the price to total pricesToAdd array
            pricesToAddTemp.add(ticketPrice);
        }

        // Second ticket free if:
        // - order is marked as Student order
        // - if day == ma-do
        if(this.isStudentOrder || (dayOfWeek >= 1 && dayOfWeek <= 4)){
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

    public void export(TicketExportFormat exportFormat)
    {
        // 1.2

        // Bases on the string respresentations of the tickets (toString), write
        // the ticket to a file with naming convention Order_<orderNr>.txt of
        // Order_<orderNr>.json
    }
}
