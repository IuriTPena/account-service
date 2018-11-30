package com.cognizant.account.domain;

public class Ticket {
    private String ticketNumber;

    public Ticket() {
    }

    public Ticket(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNumber='" + ticketNumber + '\'' +
                '}';
    }
}
