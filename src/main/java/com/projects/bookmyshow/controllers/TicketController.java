package com.projects.bookmyshow.controllers;

import com.projects.bookmyshow.dtos.BookTicketRequestDTO;
import com.projects.bookmyshow.dtos.BookTicketResponseDTO;
import com.projects.bookmyshow.dtos.Response;
import com.projects.bookmyshow.exceptions.InvalidBookTicketRequestException;
import com.projects.bookmyshow.models.Ticket;
import com.projects.bookmyshow.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(path = "/bookTicket")
    public BookTicketResponseDTO bookTicket(BookTicketRequestDTO requestDTO) {
        BookTicketResponseDTO responseDTO = new BookTicketResponseDTO();
        try {
            validateBookRequestDTO(requestDTO);
            Ticket ticket = ticketService.bookTicket(requestDTO.getUserId(), requestDTO.getShowId(), requestDTO.getShowSeatIds());
            Response response = Response.getSuccessResponse();
            responseDTO.setResponse(response);
            responseDTO.setTicket(ticket);
        } catch (Exception e) {
            Response response = Response.getFailureResponse(e.getMessage());
            responseDTO.setResponse(response);
        }
        return responseDTO;
    }

    private void validateBookRequestDTO(BookTicketRequestDTO requestDTO) throws InvalidBookTicketRequestException {
        if(requestDTO.getUserId() < 1) {
            throw new InvalidBookTicketRequestException("User ID cannot be negative");
        } else if(requestDTO.getShowId() < 1) {
            throw new InvalidBookTicketRequestException("Show ID cannot be negative");
        } else if(requestDTO.getShowSeatIds() == null || requestDTO.getShowSeatIds().isEmpty()) {
            throw new InvalidBookTicketRequestException("Seat IDs must be present");
        }
    }

}
