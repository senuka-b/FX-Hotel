package edu.icet.senuka.FXHotelManager.repository.custom.impl;

import edu.icet.senuka.FXHotelManager.entity.user.UserEntity;
import edu.icet.senuka.FXHotelManager.repository.custom.UserDao;

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
        return null;
    }
}
