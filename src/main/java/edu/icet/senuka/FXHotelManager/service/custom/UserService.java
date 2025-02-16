package edu.icet.senuka.FXHotelManager.service.custom;

import edu.icet.senuka.FXHotelManager.dto.User;
import edu.icet.senuka.FXHotelManager.service.SuperService;

public interface UserService extends SuperService {
    public boolean login(User user);
    public boolean signup(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);
}
