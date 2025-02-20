package edu.icet.senuka.fxhotel_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInOut {
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Double totalPrice = 0d;
}
