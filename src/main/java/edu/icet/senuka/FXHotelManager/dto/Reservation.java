package edu.icet.senuka.FXHotelManager.dto;

import edu.icet.senuka.FXHotelManager.util.types.ReservationStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

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
            return new SimpleDateFormat("yyyy/MM/dd").format(checkInOut.getCheckInDate());

        else return "Not specified!";
    }

    public String getCheckOutDateValue() {
        if (checkInOut.getCheckOutDate() != null)
            return new SimpleDateFormat("yyyy-MM-dd").format(checkInOut.getCheckOutDate());

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

}
