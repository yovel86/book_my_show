package com.projects.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Ticket extends BaseModel {

    @ManyToOne
    private Movie movie;
    @ManyToOne
    private MyShow myShow;
    @OneToMany
    private List<ShowSeat> showSeats;
    @ManyToOne
    private User user;

}
