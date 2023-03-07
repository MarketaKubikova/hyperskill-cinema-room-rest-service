package cinema.exception;

import cinema.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CinemaExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SeatAlreadyPurchasedException.class)
    public ResponseEntity<ErrorDetails> handleSeatAlreadyPurchasedException(SeatAlreadyPurchasedException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatOutOfBoundsException.class)
    public ResponseEntity<ErrorDetails> handleSeatOutOfBoundsException(SeatOutOfBoundsException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleTicketNotFoundException(TicketNotFoundException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorDetails> handleInvalidPasswordException(InvalidPasswordException exception) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }
}
