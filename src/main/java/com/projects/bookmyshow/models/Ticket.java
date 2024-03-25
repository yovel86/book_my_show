package com.projects.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "tickets")
public class Ticket extends BaseModel {

    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Show show;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "ticket")
    private List<ShowSeat> showSeats;

}
