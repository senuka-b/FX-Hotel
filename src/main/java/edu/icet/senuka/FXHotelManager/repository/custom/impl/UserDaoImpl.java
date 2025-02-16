package edu.icet.senuka.FXHotelManager.repository.custom.impl;

import edu.icet.senuka.FXHotelManager.entity.user.UserEntity;
import edu.icet.senuka.FXHotelManager.repository.custom.UserDao;
import edu.icet.senuka.FXHotelManager.util.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(UserEntity entity) {
        return false;
    }

    @Override
    public boolean update(Long id, UserEntity entity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
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
}
