package com.adepo.adeline.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, length = 50)
    private String bookingNumber;
    @ManyToOne
    private Flight flight;

    private Date bookingDate;

    @ManyToOne
    private Passenger passenger;

    private int seatNumber;

    public Booking() {

    }
    public Booking(String bookingNumber, Flight flight, Date bookingDate, Passenger passenger, int seatNumber) {
        this.bookingNumber = bookingNumber;
        this.flight = flight;
        this.bookingDate = bookingDate;
        this.passenger = passenger;
        this.seatNumber = seatNumber;
    }

    public Booking(String bookingNumber, Flight flight, java.sql.Date bookingDate, Passenger passenger) {

    }

    public int getId() {
        return id;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

}
