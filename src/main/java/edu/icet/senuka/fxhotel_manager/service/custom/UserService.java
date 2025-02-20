package edu.icet.senuka.fxhotel_manager.service.custom;

import edu.icet.senuka.fxhotel_manager.dto.User;
import edu.icet.senuka.fxhotel_manager.service.SuperService;

import java.util.List;

public interface UserService extends SuperService {
    public User login(User user);
    public boolean signup(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);
    public List<User> getAll();
    public List<User> getSearchResults(String searchQuery, Integer limit);

}
