package edu.icet.senuka.FXHotelManager.service.custom;

import edu.icet.senuka.FXHotelManager.dto.Payment;
import edu.icet.senuka.FXHotelManager.service.SuperService;

import java.util.List;

public interface PaymentService extends SuperService {
    public boolean finalizePayment(Payment payment);
    public List<Payment> getAll();
}
