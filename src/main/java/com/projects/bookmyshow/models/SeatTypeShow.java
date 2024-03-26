package com.projects.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "seat_type_shows")
public class SeatTypeShow extends BaseModel {

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    @ManyToOne
    private Show show;
    private double price;

}
