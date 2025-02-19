package edu.icet.senuka.FXHotelManager.dto;

import edu.icet.senuka.FXHotelManager.util.types.PaymentType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {
    private Long id;
    private Reservation reservation;
    private Double amountPaid;
    private PaymentType paymentType;
}
