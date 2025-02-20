package edu.icet.senuka.fxhotel_manager.service.custom.impl;

import com.google.inject.Inject;
import edu.icet.senuka.fxhotel_manager.dto.Payment;
import edu.icet.senuka.fxhotel_manager.entity.PaymentEntity;
import edu.icet.senuka.fxhotel_manager.repository.custom.PaymentDao;
import edu.icet.senuka.fxhotel_manager.service.custom.PaymentService;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    @Inject
    private PaymentDao dao;

    private ModelMapper mapper = new ModelMapper();

    @Override
    public boolean finalizePayment(Payment payment) {
        return dao.save(mapper.map(payment, PaymentEntity.class));
    }

    @Override
    public List<Payment> getAll() {
        List<Payment> paymentList = new ArrayList<>();

        dao.getAll().forEach(paymentEntity -> paymentList.add(
                mapper.map(paymentEntity, Payment.class)
        ));

        return paymentList;
    }
}
