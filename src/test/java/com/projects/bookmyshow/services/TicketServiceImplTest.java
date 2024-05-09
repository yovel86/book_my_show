package com.projects.bookmyshow.services;

import com.projects.bookmyshow.repositories.ShowRepository;
import com.projects.bookmyshow.repositories.ShowSeatRepository;
import com.projects.bookmyshow.repositories.TicketRepository;
import com.projects.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TicketServiceImplTest {

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ShowRepository showRepository;
    @MockBean
    private ShowSeatRepository showSeatRepository;
    @MockBean
    private TicketRepository ticketRepository;
    @Autowired
    private TicketService ticketService;

    

}