package edu.icet.senuka.FXHotelManager.service.custom.impl;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.dto.Room;
import edu.icet.senuka.FXHotelManager.entity.RoomEntity;
import edu.icet.senuka.FXHotelManager.repository.custom.RoomDao;
import edu.icet.senuka.FXHotelManager.service.custom.RoomService;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {

    @Inject
    private RoomDao dao;

    private ModelMapper mapper = new ModelMapper();

    @Override
    public List<Room> getAll() {
        List<Room> roomList = new ArrayList<>();

        dao.getAll().forEach(roomEntity -> roomList.add(mapper.map(roomEntity, Room.class)));

        return roomList;
    }

    @Override
    public boolean addRoom(Room room) {
        return dao.save(mapper.map(room, RoomEntity.class));
    }

    @Override
    public boolean updateRoom(Room room) {
        return dao.update(mapper.map(room, RoomEntity.class));

    }

    @Override
    public boolean deleteRoom(Room room) {
        return dao.delete(mapper.map(room, RoomEntity.class));
    }
}
