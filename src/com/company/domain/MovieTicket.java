package com.company.domain;

public class MovieTicket
{
    private MovieScreening movieScreening;
    private boolean isPremiumTicket;

    private int seatRow;
    private int seatNr;

    public MovieTicket(
            MovieScreening movieScreening,
            boolean isPremiumTicket,
            int seatRow,
            int seatNr)
    {
        this.movieScreening = movieScreening;
        this.isPremiumTicket = isPremiumTicket;
        this.seatRow = seatRow;
        this.seatNr = seatNr;
    }

    public MovieScreening getMovieScreening() {
        return movieScreening;
    }

    public boolean isPremiumTicket()
    {
        return isPremiumTicket;
    }

    public double getPrice()
    {
        return movieScreening.getPricePerSeat();
    }

    @Override
    public String toString() {
        return movieScreening.toString() + " - row " + seatRow + ", seat " + seatNr +
                (isPremiumTicket ? " (Premium)" : "");
    }
}
