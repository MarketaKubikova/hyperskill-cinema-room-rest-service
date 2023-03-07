package cinema.domain;

import cinema.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class CinemaRoom {
    private int totalRows;
    private int totalColumns;
    private List<Seat> availableSeats;

    public CinemaRoom() {
    }

    public CinemaRoom(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = prepareAvailableSeats();
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    private List<Seat> prepareAvailableSeats() {
        List<Seat> list = new ArrayList<>();

        for (int i = 1; i <= this.totalRows; i++) {
            for (int j = 1; j <= this.totalColumns; j++) {
                Seat seat = new Seat();
                seat.setRow(i);
                seat.setColumn(j);
                if (i <= 4) {
                    seat.setPrice(Constants.PRICE);
                } else seat.setPrice(Constants.CHEAPER_PRICE);
                list.add(seat);
            }
        }
        return list;
    }
}
