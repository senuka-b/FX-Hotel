package edu.icet.senuka.fxhotel_manager.service.custom.impl;

import com.google.inject.Inject;
import edu.icet.senuka.fxhotel_manager.dto.User;
import edu.icet.senuka.fxhotel_manager.entity.UserEntity;
import edu.icet.senuka.fxhotel_manager.repository.custom.UserDao;
import edu.icet.senuka.fxhotel_manager.service.custom.UserService;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Inject
    private UserDao dao;

    private ModelMapper mapper = new ModelMapper();

    @Override
    public User login(User user) {
        UserEntity userEntity = dao.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());

        return userEntity != null ? mapper.map(userEntity, User.class) : null;
    }

    @Override
    public boolean signup(User user) {
        return dao.save(mapper.map(user, UserEntity.class));
    }

    @Override
    public boolean updateUser(User user) {
        return dao.update(mapper.map(user, UserEntity.class));
    }

    @Override
    public boolean deleteUser(User user) {
        return dao.delete(mapper.map(user, UserEntity.class));
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        dao.getAll().forEach(entity -> userList.add(mapper.map(entity, User.class)));

        return userList;
    }

    @Override
    public List<User> getSearchResults(String searchQuery, Integer limit) {
        List<User> searchResults = new ArrayList<>();

        dao.getSearchResults(searchQuery, limit).forEach(
                userEntity -> searchResults.add(mapper.map(userEntity, User.class))
        );

        return searchResults;
    }
}
