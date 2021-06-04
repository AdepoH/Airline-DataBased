package com.adepo.adeline.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(unique = true, length = 50)
    private String flightNumber;

    @NotNull
    @ManyToOne
    private Aircraft aircraft;

    private Date takeOffTime;
    private Date arrivalTime;

    private String takeOffPoint;
    private String destination;

    private int availableSeats;

    public Flight() {

    }

    public Flight(String flightNumber, Aircraft aircraft, Date takeOffTime, Date arrivalTime, String takeOffPoint, String destination, int availableSeats) {
        this.flightNumber = flightNumber;
        this.aircraft = aircraft;
        this.takeOffTime = takeOffTime;
        this.arrivalTime = arrivalTime;
        this.takeOffPoint = takeOffPoint;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

    public Flight(String flightNumber, Date takeOffTime, Date arrivalTime, String takeOffPoint, String destination, int availableSeats) {
        this.flightNumber = flightNumber;
        this.takeOffTime = takeOffTime;
        this.arrivalTime = arrivalTime;
        this.takeOffPoint = takeOffPoint;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

    public int getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Date getTakeOffTime() {
        return takeOffTime;
    }

    public void setTakeOffTime(Date takeOffTime) {
        this.takeOffTime = takeOffTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTakeOffPoint() {
        return takeOffPoint;
    }

    public void setTakeOffPoint(String takeOffPoint) {
        this.takeOffPoint = takeOffPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
