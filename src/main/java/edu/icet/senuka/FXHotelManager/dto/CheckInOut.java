package edu.icet.senuka.FXHotelManager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInOut {
    private Long id;
    private Date checkInDate;
    private Date checkOutDate;
    private Double totalPrice = 0d;
}
