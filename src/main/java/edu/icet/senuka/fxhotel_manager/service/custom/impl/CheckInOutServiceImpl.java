package edu.icet.senuka.fxhotel_manager.service.custom.impl;

import com.google.inject.Inject;
import edu.icet.senuka.fxhotel_manager.dto.CheckInOut;
import edu.icet.senuka.fxhotel_manager.dto.Room;
import edu.icet.senuka.fxhotel_manager.entity.CheckInOutEntity;
import edu.icet.senuka.fxhotel_manager.entity.RoomEntity;
import edu.icet.senuka.fxhotel_manager.repository.custom.CheckInOutDao;
import edu.icet.senuka.fxhotel_manager.repository.custom.RoomDao;
import edu.icet.senuka.fxhotel_manager.service.custom.CheckInOutService;
import edu.icet.senuka.fxhotel_manager.util.types.AvailabilityType;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CheckInOutServiceImpl implements CheckInOutService {

    @Inject
    private CheckInOutDao dao;

    @Inject
    private RoomDao roomDao;

    private ModelMapper mapper = new ModelMapper();

    @Override
    public CheckInOutEntity addEmptyCheckInOut() {
        return dao.addEmptyCheckInOut(
                CheckInOutEntity.builder().totalPrice(0d).build()
        );
    }


    @Override
    public boolean markCheckIn(CheckInOut checkInOut, Room room) {

        LocalDate localDate = checkInOut.getCheckInDate();

        if (!localDate.isAfter(LocalDate.now())) {

            room.setAvailability(AvailabilityType.Occupied); // When Customer checks in, the room is occupied.

        } else {
            room.setAvailability(AvailabilityType.Booked);
        }

        return dao.update(mapper.map(checkInOut, CheckInOutEntity.class))
                && roomDao.update(mapper.map(room, RoomEntity.class));
    }

    @Override
    public boolean markCheckOut(CheckInOut checkInOut, Room room) {
        room.setAvailability(AvailabilityType.Available);

        if (checkInOut.getCheckInDate() != null) {

            LocalDate minimumDate = checkInOut.getCheckInDate().plusDays(1);
            LocalDate localCheckOutDate = checkInOut.getCheckOutDate();

            if (localCheckOutDate.isAfter(minimumDate)) {

                checkInOut.setTotalPrice(
                        room.getPricePerNight() * (ChronoUnit.DAYS.between(checkInOut.getCheckInDate(), localCheckOutDate))
                );

                return dao.update(mapper.map(checkInOut, CheckInOutEntity.class))
                        && roomDao.update(mapper.map(room, RoomEntity.class));
            }

        }

        return false;
    }
}
