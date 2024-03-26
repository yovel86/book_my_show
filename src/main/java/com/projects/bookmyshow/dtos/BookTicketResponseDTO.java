package com.projects.bookmyshow.dtos;

import com.projects.bookmyshow.models.Ticket;
import lombok.Data;

@Data
public class BookTicketResponseDTO {

    private Ticket ticket;
    private Response response;

}
