package edu.icet.senuka.fxhotel_manager.repository.custom.impl;

import edu.icet.senuka.fxhotel_manager.entity.UserEntity;
import edu.icet.senuka.fxhotel_manager.repository.custom.UserDao;
import edu.icet.senuka.fxhotel_manager.util.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(UserEntity entity) {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);
        transaction.commit();

        return true;
    }

    @Override
    public boolean update(UserEntity entity) {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        session.merge(entity);
        transaction.commit();

        return true;
    }

    @Override
    public boolean delete(UserEntity entity) {
        Session session = HibernateConfig.getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(entity);
        transaction.commit();

        return true;
    }

    @Override
    public List<UserEntity> getAll() {
        Session session = HibernateConfig.getSession();
        return session.createQuery("From UserEntity ", UserEntity.class).getResultList();

    }

    @Override
    public UserEntity getUserByUsernameAndPassword(String username, String password) {
        Session session = HibernateConfig.getSession();
        Query<UserEntity> query = session.createQuery("FROM UserEntity u WHERE u.username = :username AND u.password = :password", UserEntity.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        return query.getSingleResultOrNull();

    }

    @Override
    public List<UserEntity> getSearchResults(String searchQuery, Integer limit) {
        Session session = HibernateConfig.getSession();

        Transaction transaction = session.beginTransaction();

        List<UserEntity> searchResults = session.createQuery(
                        "FROM UserEntity u WHERE u.username LIKE :query",
                        UserEntity.class)
                .setParameter("query", "%" + searchQuery + "%")
                .setMaxResults(limit)
                .getResultList();

        transaction.commit();
        return searchResults;
    }
}
