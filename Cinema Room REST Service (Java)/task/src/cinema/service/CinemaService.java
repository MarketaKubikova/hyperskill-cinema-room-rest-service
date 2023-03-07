package cinema.service;

import cinema.domain.CinemaRoom;
import cinema.domain.Seat;
import cinema.domain.Statistics;
import cinema.domain.Ticket;
import cinema.model.TicketResponseDTO;

public interface CinemaService {
    CinemaRoom getAllSeats();
    Ticket buyTicket(Seat seat);
    TicketResponseDTO returnTicket(Ticket ticket);
    Statistics showStatistics(String password);
}
