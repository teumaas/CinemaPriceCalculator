package com.company.domain;

import com.company.domain.price_calculation.PriceCalculator;
import com.company.domain.price_calculation.RegularOrderCalculator;
import com.company.domain.price_calculation.StudentOrderCalculator;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

    public void setTickets(ArrayList<MovieTicket> tickets) {
        this.tickets = tickets;
    }

    public ArrayList<MovieTicket> getTickets() { return this.tickets; }

    public boolean isStudentOrder() { return this.isStudentOrder; }

    public double calculatePrice() {
        PriceCalculator calculator;
        if (isStudentOrder) {
            calculator = new PriceCalculator(new StudentOrderCalculator());
        } else {
            calculator = new PriceCalculator(new RegularOrderCalculator());
        }
        return calculator.calculatePrice(this);
    }

}
