package com.projects.bookmyshow.services;

import com.projects.bookmyshow.models.Ticket;

import java.util.List;

public interface TicketService {

    Ticket bookTicket(int userId, int showId, List<Integer> showSeatIds) throws Exception;

}
