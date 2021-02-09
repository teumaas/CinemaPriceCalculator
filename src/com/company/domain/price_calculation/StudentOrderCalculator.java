package com.company.domain.price_calculation;

import com.company.domain.MovieTicket;
import com.company.domain.Order;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class StudentOrderCalculator implements IPriceCalculator {
    @Override
    public double calculatePrice(Order order) {
        ArrayList<Double> pricesToAddTemp = new ArrayList<>();
        boolean mondayToThursday = false;

        // Iterate over every ticket
        for (MovieTicket ticket : order.getTickets()){

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
                ticketPrice = ticketPrice + 2.0;
            }

            // 10% discount on order if
            // - 6 tickets or more on order
            // & weekend day
            // & student order
            if(order.getTickets().size() >= 6
                    && (dayOfWeek >= 6 && dayOfWeek <= 7)
                    && !order.isStudentOrder()){
                ticketPrice = ticketPrice * 0.9;
            }

            // Add the price to total pricesToAdd array
            pricesToAddTemp.add(ticketPrice);
        }

        // Second ticket free if:
        // - order is marked as Student order
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
        double finalPrice = 0.0;
        for(Double price : pricesToAddFinal){
            finalPrice = finalPrice + price;
        }
        return finalPrice;

    }
}
