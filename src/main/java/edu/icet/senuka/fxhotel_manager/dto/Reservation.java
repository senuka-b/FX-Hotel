package edu.icet.senuka.fxhotel_manager.dto;

import edu.icet.senuka.fxhotel_manager.util.types.ReservationStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Reservation implements Cloneable {

    private Long id;
    private CheckInOut checkInOut;
    private Customer customer;
    private Room room;
    private ReservationStatusType status;

    public String getCheckInDateValue() {
        if (checkInOut.getCheckInDate() != null)
            return checkInOut.getCheckInDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        else return "Not specified!";
    }

    public String getCheckOutDateValue() {
        if (checkInOut.getCheckOutDate() != null)
            return checkInOut.getCheckOutDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        else return "Not specified!";


    }

    public String getCustomerNameValue() {
        return customer.getFullName();
    }

    public String getCustomerPhoneNumberValue() {
        return customer.getPhoneNumber();
    }

    public String getRoomIdValue() {
        return room.getId()+"";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // Returning a clone of the current object
        return super.clone();
    }

    @Override
    public String toString() {
        return String.format("[%d] | Customer: %s - Phone : %s | Room #%s", id, getCustomerNameValue(), getCustomerPhoneNumberValue(),  getRoomIdValue());
    }

}
