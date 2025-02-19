package edu.icet.senuka.FXHotelManager.repository.custom.impl;

import edu.icet.senuka.FXHotelManager.entity.CheckInOutEntity;
import edu.icet.senuka.FXHotelManager.entity.ReservationEntity;
import edu.icet.senuka.FXHotelManager.repository.custom.ReservationDao;
import edu.icet.senuka.FXHotelManager.util.HibernateConfig;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.util.List;

public class ReservationDaoImpl implements ReservationDao {
    @Override
    public boolean save(ReservationEntity entity) {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        CheckInOutEntity checkInOut = entity.getCheckInOut();

        if (checkInOut.getId() == null) {
            session.persist(checkInOut);
        } else {
            checkInOut = session.merge(checkInOut);
        }

        entity.setCheckInOut(checkInOut);

        session.persist(entity);
        transaction.commit();

        return true;
    }

    @Override
    public boolean update(ReservationEntity entity) {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        session.merge(entity);
        transaction.commit();

        return true;
    }

    @Override
    public boolean delete(ReservationEntity entity) {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(entity);
        transaction.commit();

        return true;
    }

    @Override
    public List<ReservationEntity> getAll() {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        List<ReservationEntity> from_reservationEntity = session.createQuery("FROM ReservationEntity", ReservationEntity.class).list();
        transaction.commit();

        return from_reservationEntity;

    }

    @Override
    public List<ReservationEntity> getCheckOutReservations() {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        List<ReservationEntity> from_reservationEntity = session.createQuery("FROM ReservationEntity r WHERE  r.checkInOut.checkOutDate IS NOT NULL", ReservationEntity.class).list();
        transaction.commit();

        return from_reservationEntity;
    }
}
