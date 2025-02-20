package edu.icet.senuka.FXHotelManager.service.custom;

import edu.icet.senuka.FXHotelManager.dto.Customer;
import edu.icet.senuka.FXHotelManager.dto.User;
import edu.icet.senuka.FXHotelManager.service.SuperService;

import java.util.List;

public interface UserService extends SuperService {
    public User login(User user);
    public boolean signup(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);
    public List<User> getAll();
    public List<User> getSearchResults(String searchQuery, Integer limit);

}
