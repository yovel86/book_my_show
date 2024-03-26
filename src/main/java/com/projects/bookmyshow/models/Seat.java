package com.projects.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "seats")
public class Seat extends BaseModel {

    private String name;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    @ManyToOne
    private Screen screen;

}
