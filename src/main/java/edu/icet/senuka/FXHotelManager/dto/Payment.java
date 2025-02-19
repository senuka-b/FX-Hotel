package edu.icet.senuka.FXHotelManager.dto;

import edu.icet.senuka.FXHotelManager.util.types.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private Long id;
    private Reservation reservation;
    private Double amountPaid;
    private PaymentType paymentType;
}
