package edu.icet.senuka.FXHotelManager.service.custom.impl;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.dto.User;
import edu.icet.senuka.FXHotelManager.entity.UserEntity;
import edu.icet.senuka.FXHotelManager.repository.custom.UserDao;
import edu.icet.senuka.FXHotelManager.service.custom.UserService;

public class UserServiceImpl implements UserService {

    @Inject
    private UserDao dao;

    @Override
    public boolean login(User user) {
        UserEntity userEntity = dao.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());

        return userEntity != null;
    }

    @Override
    public boolean signup(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}
