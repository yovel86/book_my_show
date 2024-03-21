package com.projects.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class MyShow extends BaseModel {

    @ManyToOne
    private Screen screen;
    @ManyToOne
    private Movie movie;
    private Date startTime;

}
