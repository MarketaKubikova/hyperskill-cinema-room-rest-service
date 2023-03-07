package cinema.service;

import cinema.domain.CinemaRoom;
import cinema.domain.Seat;
import cinema.domain.Statistics;
import cinema.domain.Ticket;
import cinema.exception.InvalidPasswordException;
import cinema.exception.SeatAlreadyPurchasedException;
import cinema.exception.SeatOutOfBoundsException;
import cinema.exception.TicketNotFoundException;
import cinema.model.TicketResponseDTO;
import cinema.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CinemaServiceImpl implements CinemaService {
    CinemaRoom room = new CinemaRoom(Constants.TOTAL_ROWS, Constants.TOTAL_COLUMNS);
    List<Ticket> purchasedTicketList = new ArrayList<>();

    @Override
    public CinemaRoom getAllSeats() {
        return room;
    }

    @Override
    public Ticket buyTicket(Seat seat) {
        Seat reservedSeat = findSeat(seat);

        Ticket ticket = new Ticket();
        ticket.setToken(UUID.randomUUID());
        ticket.setSeat(reservedSeat);

        room.getAvailableSeats().remove(reservedSeat);
        purchasedTicketList.add(ticket);

        return ticket;
    }

    @Override
    public TicketResponseDTO returnTicket(Ticket ticket) {
        Ticket returningTicket = purchasedTicketList.stream()
                .filter(t -> t.getToken().equals(ticket.getToken()))
                .findFirst()
                .orElseThrow(TicketNotFoundException::new);

        room.getAvailableSeats().add(returningTicket.getSeat());
        room.getAvailableSeats().sort(Comparator.comparingInt(Seat::getRow).thenComparing(Seat::getColumn));
        purchasedTicketList.remove(returningTicket);

        return new TicketResponseDTO(returningTicket.getSeat());
    }

    @Override
    public Statistics showStatistics(String password) {
        if (password != null && password.equals(Constants.SECRET_PASSWORD)) {
            Statistics stats = new Statistics();

            int income = purchasedTicketList.stream()
                    .map(t -> t.getSeat().getPrice())
                    .mapToInt(Integer::intValue)
                    .sum();

            stats.setCurrentIncome(income);
            stats.setNumberOfAvailableSeats(room.getAvailableSeats().size());
            stats.setNumberOfPurchasedTickets(purchasedTicketList.size());

            return stats;
        } else throw new InvalidPasswordException();
    }

    private Seat findSeat(Seat seat) {
        if (seat.getRow() > Constants.TOTAL_ROWS || seat.getRow() < 1 ||
                seat.getColumn() > Constants.TOTAL_COLUMNS || seat.getColumn() < 1) {
            throw new SeatOutOfBoundsException();
        }

        return room.getAvailableSeats().stream()
                .filter(s -> s.getRow() == seat.getRow() && s.getColumn() == seat.getColumn())
                .findFirst()
                .orElseThrow(SeatAlreadyPurchasedException::new);
    }
}
