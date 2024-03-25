package com.projects.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "show_seats")
public class ShowSeat extends BaseModel {

    private SeatStatus seatStatus;
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;
    @ManyToOne
    private User user;
    @ManyToOne
    private Ticket ticket;

}
