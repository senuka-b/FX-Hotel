package edu.icet.senuka.fxhotel_manager.dto;

import edu.icet.senuka.fxhotel_manager.util.types.PaymentType;
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
