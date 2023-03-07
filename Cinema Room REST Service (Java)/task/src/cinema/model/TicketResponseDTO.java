package cinema.model;

import cinema.domain.Seat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketResponseDTO {
    @JsonProperty(value = "returned_ticket")
    Seat seat;

    public TicketResponseDTO(Seat seat) {
        this.seat = seat;
    }
}
