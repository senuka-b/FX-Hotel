package edu.icet.senuka.fxhotel_manager.service.custom;

import edu.icet.senuka.fxhotel_manager.dto.Room;
import edu.icet.senuka.fxhotel_manager.service.SuperService;

import java.util.List;

public interface RoomService extends SuperService {
    public List<Room> getAll();
    public boolean addRoom(Room room);
    public boolean updateRoom(Room room);
    public boolean deleteRoom(Room room);
}
