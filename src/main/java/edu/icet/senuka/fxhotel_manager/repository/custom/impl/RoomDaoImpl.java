package edu.icet.senuka.fxhotel_manager.repository.custom.impl;

import edu.icet.senuka.fxhotel_manager.entity.RoomEntity;
import edu.icet.senuka.fxhotel_manager.repository.custom.RoomDao;
import edu.icet.senuka.fxhotel_manager.util.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomDaoImpl implements RoomDao {
    @Override
    public boolean save(RoomEntity entity) {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);
        transaction.commit();

        return true;
    }

    @Override
    public boolean update(RoomEntity entity) {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        session.merge(entity);
        transaction.commit();

        return true;
    }

    @Override
    public boolean delete(RoomEntity entity) {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(entity);
        transaction.commit();

        return true;
    }

    @Override
    public List<RoomEntity> getAll() {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        List<RoomEntity> from_roomEntity = session.createQuery("FROM RoomEntity", RoomEntity.class).list();
        transaction.commit();

        return from_roomEntity;
    }
}
