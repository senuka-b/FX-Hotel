package edu.icet.senuka.fxhotel_manager.repository.custom.impl;

import edu.icet.senuka.fxhotel_manager.entity.CheckInOutEntity;
import edu.icet.senuka.fxhotel_manager.repository.custom.CheckInOutDao;
import edu.icet.senuka.fxhotel_manager.util.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CheckInOutDaoImpl implements CheckInOutDao {
    @Override
    public boolean save(CheckInOutEntity entity) {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();


        session.persist(entity);
        transaction.commit();

        return true;
    }

    @Override
    public boolean update(CheckInOutEntity entity) {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        session.merge(entity);
        transaction.commit();

        return true;
    }

    @Override
    public boolean delete(CheckInOutEntity entity) {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(entity);
        transaction.commit();

        return true;
    }

    @Override
    public List<CheckInOutEntity> getAll() {
        Session session = HibernateConfig.getSession();
        return session.createQuery("FROM CheckInOutEntity", CheckInOutEntity.class).list();
    }

    @Override
    public CheckInOutEntity addEmptyCheckInOut(CheckInOutEntity entity) { Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);
        transaction.commit();

        return entity;
    }
}
