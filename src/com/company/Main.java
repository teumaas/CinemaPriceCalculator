package com.company;

import com.company.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Voorbereiding op werkcollege 1");

        ArrayList<MovieTicket> movieTicketSampleList = new ArrayList<>();
        Movie sampleMovie = new Movie("The Algorithm");
        MovieScreening sampleScreening = new MovieScreening(sampleMovie, LocalDateTime.now(), 11.0);

        MovieTicket sampleTicketOne = new MovieTicket(sampleScreening, false, 5, 1);
        MovieTicket sampleTicketTwo = new MovieTicket(sampleScreening, false, 5, 2);
        MovieTicket sampleTicketThree = new MovieTicket(sampleScreening, false, 5, 3);

        ArrayList<MovieTicket> tickets = new ArrayList<>();
        tickets.add(sampleTicketOne);
        tickets.add(sampleTicketTwo);
        tickets.add(sampleTicketThree);

        Order order = new Order(1, true);
        order.setTickets(tickets);
        System.out.println("Total order price: €" + order.calculatePrice() + "");

        order.export(TicketExportFormat.JSON);
    }
}
