package edu.icet.senuka.fxhotel_manager.repository.custom.impl;

import edu.icet.senuka.fxhotel_manager.entity.PaymentEntity;
import edu.icet.senuka.fxhotel_manager.repository.custom.PaymentDao;
import edu.icet.senuka.fxhotel_manager.util.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public boolean save(PaymentEntity entity) {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        session.merge(entity);
        transaction.commit();

        return true;
    }

    @Override
    public boolean update(PaymentEntity entity) {
        return false;
    }

    @Override
    public boolean delete(PaymentEntity entity) {
        return false;
    }

    @Override
    public List<PaymentEntity> getAll() {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        List<PaymentEntity> from_paymentEntity = session.createQuery("FROM PaymentEntity ", PaymentEntity.class).list();
        transaction.commit();

        return from_paymentEntity;
    }
}
