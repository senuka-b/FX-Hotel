package edu.icet.senuka.FXHotelManager.repository.custom;

import edu.icet.senuka.FXHotelManager.entity.user.UserEntity;
import edu.icet.senuka.FXHotelManager.repository.CrudRepository;

public interface UserDao extends CrudRepository<UserEntity, Long> {
    public UserEntity getUserByUsernameAndPassword(String username, String password);
}
