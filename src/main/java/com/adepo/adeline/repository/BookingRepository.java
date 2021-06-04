package com.adepo.adeline.repository;

import com.adepo.adeline.model.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

    Booking findBookingById(int id);
    Booking findBookingByBookingNumber(String bookingNumber);

}
