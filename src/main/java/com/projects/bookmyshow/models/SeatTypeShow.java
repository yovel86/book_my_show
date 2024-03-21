package com.projects.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class SeatTypeShow extends BaseModel {

    private SeatType seatType;
    @ManyToOne
    private MyShow myShow;
    private double price;

}
