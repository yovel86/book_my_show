package com.projects.bookmyshow.services;

import com.projects.bookmyshow.exceptions.InvalidBookTicketRequestException;
import com.projects.bookmyshow.exceptions.SeatsUnavailableException;
import com.projects.bookmyshow.models.*;
import com.projects.bookmyshow.repositories.ShowRepository;
import com.projects.bookmyshow.repositories.ShowSeatRepository;
import com.projects.bookmyshow.repositories.TicketRepository;
import com.projects.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.ticketRepository = ticketRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Ticket bookTicket(int userId, int showId, List<Integer> showSeatIds) throws Exception {
        /*
        1. Check if the user is valid
        2. ShowId in showSeatIds and showId should match
        3. Start Transaction (Serializable)
            -> SELECT * FROM show_seats WHERE id IN (showSeatIds) AND seat_status = 'AVAILABLE' FOR UPDATE
            -> If (All seats are NOT available)
                throw error and rollback the transaction
            -> Update show_seats SET seat_status = 'BLOCKED' WHERE ids IN (showSeatIds)
        4. Generate Ticket object, store in DB & return
         */
        Optional<User> userOptional = this.userRepository.findById(userId);
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
        } else throw new InvalidBookTicketRequestException("Invalid User");

        Show show = this.showRepository.findById(showId).orElseThrow(() -> new InvalidBookTicketRequestException("Invalid Show"));

        List<ShowSeat> showSeats = this.showSeatRepository.findShowSeatByIdInAndSeatStatus_AvailableAndShow(showSeatIds, show);

        if(showSeats.size() != showSeatIds.size()) {
            throw new SeatsUnavailableException("Some of the seats you are trying to book are not available");
        }

        for(ShowSeat showSeat: showSeats) {
            showSeat.setUser(user);
            showSeat.setSeatStatus(SeatStatus.BLOCKED);
        }
        this.showSeatRepository.saveAll(showSeats);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setMovie(show.getMovie());
        ticket.setShowSeats(showSeats);

        return ticketRepository.save(ticket);
    }

}
