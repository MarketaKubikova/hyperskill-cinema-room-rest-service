package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException() {
        super("Wrong token!");
    }
}
