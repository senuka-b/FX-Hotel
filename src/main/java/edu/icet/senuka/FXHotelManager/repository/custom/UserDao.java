package edu.icet.senuka.FXHotelManager.repository.custom;

import edu.icet.senuka.FXHotelManager.entity.UserEntity;
import edu.icet.senuka.FXHotelManager.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<UserEntity, Long> {
    public UserEntity getUserByUsernameAndPassword(String username, String password);
    public List<UserEntity> getSearchResults(String searchQuery, Integer limit);
}
