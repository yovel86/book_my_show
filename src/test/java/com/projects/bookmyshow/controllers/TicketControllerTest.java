package com.projects.bookmyshow.controllers;

import com.projects.bookmyshow.dtos.BookTicketRequestDTO;
import com.projects.bookmyshow.dtos.BookTicketResponseDTO;
import com.projects.bookmyshow.dtos.ResponseStatus;
import com.projects.bookmyshow.models.Ticket;
import com.projects.bookmyshow.services.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest
class TicketControllerTest {

    /*
        Scenarios
        1. Validation fails (Negative)
        2. Ticket Service fails (Negative)
        3. Ticket Service gives a Ticker object (Positive)
    */

    @MockBean
    private TicketService ticketService;
    @Autowired
    private TicketController ticketController;

    @Test
    void testBookTicket_ShowIdNegative() throws Exception {
        // Arrange
        BookTicketRequestDTO requestDTO = new BookTicketRequestDTO();
        requestDTO.setShowId(-1);
        requestDTO.setUserId(1);
        requestDTO.setShowSeatIds(List.of(1,2,3));

        when(ticketService.bookTicket(requestDTO.getUserId(), requestDTO.getShowId(), requestDTO.getShowSeatIds()))
                .thenThrow(Exception.class);

        // Act
        BookTicketResponseDTO responseDTO = ticketController.bookTicket(requestDTO);

        // Assert
        assertNotNull(responseDTO, "ResponseDto should NOT be null");
        assertNotNull(responseDTO.getResponse());
        assertEquals(ResponseStatus.FAILURE, responseDTO.getResponse().getResponseStatus());
        assertNotNull(responseDTO.getResponse().getErrorMsg());
        assertNull(responseDTO.getTicket(), "Ticket should not be generated");
    }

    @Test
    void testBookTicket_UserIdNegative() throws Exception {
        // Arrange
        BookTicketRequestDTO requestDTO = new BookTicketRequestDTO();
        requestDTO.setShowId(1);
        requestDTO.setUserId(-1);
        requestDTO.setShowSeatIds(List.of(1,2,3));

        when(ticketService.bookTicket(requestDTO.getUserId(), requestDTO.getShowId(), requestDTO.getShowSeatIds()))
                .thenThrow(Exception.class);

        // Act
        BookTicketResponseDTO responseDTO = ticketController.bookTicket(requestDTO);

        // Assert
        assertNotNull(responseDTO, "ResponseDto should NOT be null");
        assertNotNull(responseDTO.getResponse());
        assertEquals(ResponseStatus.FAILURE, responseDTO.getResponse().getResponseStatus());
        assertNotNull(responseDTO.getResponse().getErrorMsg());
        assertNull(responseDTO.getTicket(), "Ticket should not be generated");
    }

    @Test
    void testBookTicket_ShowSeatIdsEmptyOrNull() throws Exception {
        // Arrange
        BookTicketRequestDTO requestDTO = new BookTicketRequestDTO();
        requestDTO.setShowId(1);
        requestDTO.setUserId(1);
        requestDTO.setShowSeatIds(null);

        when(ticketService.bookTicket(requestDTO.getUserId(), requestDTO.getShowId(), requestDTO.getShowSeatIds()))
                .thenThrow(Exception.class);

        // Act
        BookTicketResponseDTO responseDTO = ticketController.bookTicket(requestDTO);

        // Assert
        assertNotNull(responseDTO, "ResponseDto should NOT be null");
        assertNotNull(responseDTO.getResponse());
        assertEquals(ResponseStatus.FAILURE, responseDTO.getResponse().getResponseStatus());
        assertNotNull(responseDTO.getResponse().getErrorMsg());
        assertNull(responseDTO.getTicket(), "Ticket should not be generated");
    }

    @Test
    void testBookTicket_Positive() throws Exception {
        // Arrange
        BookTicketRequestDTO requestDTO = new BookTicketRequestDTO();
        requestDTO.setShowId(1);
        requestDTO.setUserId(1);
        requestDTO.setShowSeatIds(List.of(1, 2, 3));

        when(ticketService.bookTicket(requestDTO.getUserId(), requestDTO.getShowId(), requestDTO.getShowSeatIds()))
                .thenReturn(new Ticket());

        // Act
        BookTicketResponseDTO responseDTO = ticketController.bookTicket(requestDTO);

        // Assert
        assertNotNull(responseDTO, "ResponseDto should NOT be null");
        assertNotNull(responseDTO.getResponse());
        assertEquals(ResponseStatus.SUCCESS, responseDTO.getResponse().getResponseStatus());
        assertNull(responseDTO.getResponse().getErrorMsg());
        assertNotNull(responseDTO.getTicket(), "Ticket should be generated");
    }


}