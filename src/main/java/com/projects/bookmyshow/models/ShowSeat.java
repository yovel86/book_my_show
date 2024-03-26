package com.projects.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "show_seats")
public class ShowSeat extends BaseModel {

    @Enumerated(value = EnumType.STRING)
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
