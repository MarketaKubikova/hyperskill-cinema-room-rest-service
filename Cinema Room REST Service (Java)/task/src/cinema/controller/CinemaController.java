package cinema.controller;

import cinema.domain.CinemaRoom;
import cinema.domain.Seat;
import cinema.domain.Statistics;
import cinema.domain.Ticket;
import cinema.model.TicketResponseDTO;
import cinema.service.CinemaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaController {
    private final CinemaServiceImpl service;

    public CinemaController(CinemaServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/seats")
    public CinemaRoom getAvailableSeats() {
        return service.getAllSeats();
    }

    @PostMapping("/purchase")
    public ResponseEntity<Ticket> buyTicket(@RequestBody Seat seat) {
        return new ResponseEntity<>(service.buyTicket(seat), HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<TicketResponseDTO> returnTicket(@RequestBody Ticket ticket) {
        return new ResponseEntity<>(service.returnTicket(ticket), HttpStatus.OK);
    }

    @PostMapping("/stats")
    public ResponseEntity<Statistics> showStats(@RequestParam(value = "password", required = false) String password) {
        return new ResponseEntity<>(service.showStatistics(password), HttpStatus.OK);
    }
}
