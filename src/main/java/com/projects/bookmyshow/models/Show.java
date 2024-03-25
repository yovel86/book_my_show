package com.projects.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "shows")
public class Show extends BaseModel {

    private Date startTime;
    @ManyToOne
    private Screen screen;
    @ManyToOne
    private Movie movie;

}
