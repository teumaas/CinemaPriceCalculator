package com.company.unittests;

import com.company.domain.Movie;
import com.company.domain.MovieScreening;
import com.company.domain.MovieTicket;
import com.company.domain.Order;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTests {
    @Test
    public void testSecondTicketFreeMondayToThursdayWithoutStudentDiscount() {
        Movie sampleMovie = new Movie("The Algorithm");

        // tuesday
        LocalDateTime localDateTime =
                LocalDateTime.of(2021, 2, 2, 14, 33, 48, 123456789);

        MovieScreening sampleScreening = new MovieScreening(sampleMovie, localDateTime, 11.0);


        MovieTicket sampleTicketOne = new MovieTicket(sampleScreening, false, 5, 1);
        MovieTicket sampleTicketTwo = new MovieTicket(sampleScreening, false, 5, 2);
        MovieTicket sampleTicketThree = new MovieTicket(sampleScreening, false, 5, 3);

        ArrayList<MovieTicket> tickets = new ArrayList<>();
        tickets.add(sampleTicketOne);
        tickets.add(sampleTicketTwo);
        tickets.add(sampleTicketThree);

        Order order = new Order(1, false);
        order.setTickets(tickets);


        DayOfWeek dayOfWeekDay = localDateTime.getDayOfWeek();
        int dayOfWeek = dayOfWeekDay.getValue();

        // On Monday-thursday should return 22, else 33: testing tuesday:
        assertTrue(order.calculatePrice() == 22);
    }

    @Test
    public void testSecondTicketFreeWithStudentDiscount() {
        Movie sampleMovie = new Movie("The Algorithm");
        LocalDateTime localDateTime =
                LocalDateTime.of(2021, 2, 2, 14, 33, 48, 123456789);

        MovieScreening sampleScreening = new MovieScreening(sampleMovie, localDateTime, 11.0);

        MovieTicket sampleTicketOne = new MovieTicket(sampleScreening, false, 5, 1);
        MovieTicket sampleTicketTwo = new MovieTicket(sampleScreening, false, 5, 2);
        MovieTicket sampleTicketThree = new MovieTicket(sampleScreening, false, 5, 3);

        ArrayList<MovieTicket> tickets = new ArrayList<>();
        tickets.add(sampleTicketOne);
        tickets.add(sampleTicketTwo);
        tickets.add(sampleTicketThree);

        Order order = new Order(1, true);
        order.setTickets(tickets);

        assertTrue(order.calculatePrice() == 22);

    }

    @Test
    public void testPremiumSeatStudentCostIncrease() {
        Movie sampleMovie = new Movie("The Algorithm");
        LocalDateTime localDateTime =
                LocalDateTime.of(2021, 2, 2, 14, 33, 48, 123456789);
        MovieScreening sampleScreening = new MovieScreening(sampleMovie, localDateTime, 11.0);

        MovieTicket sampleTicketOne = new MovieTicket(sampleScreening, true, 5, 1);
        MovieTicket sampleTicketTwo = new MovieTicket(sampleScreening, true, 5, 2);
        MovieTicket sampleTicketThree = new MovieTicket(sampleScreening, true, 5, 3);

        ArrayList<MovieTicket> tickets = new ArrayList<>();
        tickets.add(sampleTicketOne);
        tickets.add(sampleTicketTwo);
        tickets.add(sampleTicketThree);

        Order order = new Order(1, true);
        order.setTickets(tickets);
        assertTrue(order.calculatePrice() == 26);

    }

    @Test
    public void testPremiumSeatRegularCostIncrease() {
        Movie sampleMovie = new Movie("The Algorithm");
        LocalDateTime localDateTime =
                LocalDateTime.of(2021, 2, 6, 14, 33, 48, 123456789);
        MovieScreening sampleScreening = new MovieScreening(sampleMovie, localDateTime, 11.0);

        MovieTicket sampleTicketOne = new MovieTicket(sampleScreening, true, 5, 1);
        MovieTicket sampleTicketTwo = new MovieTicket(sampleScreening, true, 5, 2);
        MovieTicket sampleTicketThree = new MovieTicket(sampleScreening, true, 5, 3);

        ArrayList<MovieTicket> tickets = new ArrayList<>();
        tickets.add(sampleTicketOne);
        tickets.add(sampleTicketTwo);
        tickets.add(sampleTicketThree);

        Order order = new Order(1, false);
        order.setTickets(tickets);
        System.out.println(order.calculatePrice());
        assertTrue(order.calculatePrice() == 42);

    }

    @Test
    public void testTenPercentDiscountWhenSixOrMoreTickets() {
        Movie sampleMovie = new Movie("The Algorithm");

        LocalDateTime localDateTime =
                LocalDateTime.of(2021, 2, 6, 14, 33, 48, 123456789);

        MovieScreening sampleScreening = new MovieScreening(sampleMovie, localDateTime, 11.0);

        MovieTicket sampleTicketOne = new MovieTicket(sampleScreening, false, 5, 1);
        MovieTicket sampleTicketTwo = new MovieTicket(sampleScreening, false, 5, 2);
        MovieTicket sampleTicketThree = new MovieTicket(sampleScreening, false, 5, 3);
        MovieTicket sampleTicketFour = new MovieTicket(sampleScreening, false, 5, 4);
        MovieTicket sampleTicketFive = new MovieTicket(sampleScreening, false, 5, 5);
        MovieTicket sampleTicketSix = new MovieTicket(sampleScreening, false, 5, 6);

        ArrayList<MovieTicket> tickets = new ArrayList<>();
        tickets.add(sampleTicketOne);
        tickets.add(sampleTicketTwo);
        tickets.add(sampleTicketThree);
        tickets.add(sampleTicketFour);
        tickets.add(sampleTicketFive);
        tickets.add(sampleTicketSix);

        Order order = new Order(1, false);
        order.setTickets(tickets);

        assertTrue(order.calculatePrice() == 59.4);

    }
}
