package com.company;

import com.company.domain.*;
import com.company.domain.exporting.Exporter;
import com.company.domain.exporting.JsonExporter;
import com.company.domain.exporting.TextExporter;
import com.company.domain.price_calculation.PriceCalculator;
import com.company.domain.price_calculation.RegularOrderCalculator;
import com.company.domain.price_calculation.StudentOrderCalculator;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Voorbereiding op werkcollege 1");

        //ArrayList<MovieTicket> movieTicketSampleList = new ArrayList<>();

        Movie sampleMovie = new Movie("The Algorithm");
        MovieScreening sampleScreening = new MovieScreening(sampleMovie, LocalDateTime.now(), 11.0);

        MovieTicket sampleTicketOne = new MovieTicket(sampleScreening, true, 5, 1);
        MovieTicket sampleTicketTwo = new MovieTicket(sampleScreening, false, 5, 2);
        MovieTicket sampleTicketThree = new MovieTicket(sampleScreening, false, 5, 3);

        ArrayList<MovieTicket> tickets = new ArrayList<>();
        tickets.add(sampleTicketOne);
        tickets.add(sampleTicketTwo);
        tickets.add(sampleTicketThree);

        Order order = new Order(1, true);
        order.setTickets(tickets);

        System.out.println("Price for order: " + order.calculatePrice());


        Exporter exporter1 = new Exporter(new JsonExporter());
        exporter1.exportFile(order, "foo");

        Exporter exporter2 = new Exporter(new TextExporter());
        exporter2.exportFile(order, "bar");
    }
}
