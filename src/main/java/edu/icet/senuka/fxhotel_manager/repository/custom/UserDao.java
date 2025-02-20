package edu.icet.senuka.fxhotel_manager.repository.custom;

import edu.icet.senuka.fxhotel_manager.entity.UserEntity;
import edu.icet.senuka.fxhotel_manager.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<UserEntity, Long> {
    public UserEntity getUserByUsernameAndPassword(String username, String password);
    public List<UserEntity> getSearchResults(String searchQuery, Integer limit);
}
