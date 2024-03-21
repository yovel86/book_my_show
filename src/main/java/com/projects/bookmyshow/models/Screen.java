package com.projects.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Screen extends BaseModel {

    private String name;
    @OneToMany
    private List<Seat> seats;

}
