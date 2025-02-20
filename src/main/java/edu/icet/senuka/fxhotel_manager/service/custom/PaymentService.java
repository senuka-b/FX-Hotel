package edu.icet.senuka.fxhotel_manager.service.custom;

import edu.icet.senuka.fxhotel_manager.dto.Payment;
import edu.icet.senuka.fxhotel_manager.service.SuperService;

import java.util.List;

public interface PaymentService extends SuperService {
    public boolean finalizePayment(Payment payment);
    public List<Payment> getAll();
}
