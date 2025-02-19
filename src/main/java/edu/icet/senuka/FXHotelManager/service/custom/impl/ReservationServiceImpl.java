package edu.icet.senuka.FXHotelManager.service.custom.impl;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.dto.CheckInOut;
import edu.icet.senuka.FXHotelManager.dto.Reservation;
import edu.icet.senuka.FXHotelManager.dto.Room;
import edu.icet.senuka.FXHotelManager.entity.CheckInOutEntity;
import edu.icet.senuka.FXHotelManager.entity.ReservationEntity;
import edu.icet.senuka.FXHotelManager.entity.RoomEntity;
import edu.icet.senuka.FXHotelManager.repository.custom.CheckInOutDao;
import edu.icet.senuka.FXHotelManager.repository.custom.ReservationDao;
import edu.icet.senuka.FXHotelManager.repository.custom.RoomDao;
import edu.icet.senuka.FXHotelManager.service.custom.ReservationService;
import edu.icet.senuka.FXHotelManager.util.types.AvailabilityType;
import edu.icet.senuka.FXHotelManager.util.types.ReservationStatusType;
import org.hibernate.metamodel.mapping.ModelPart;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    @Inject
    private ReservationDao dao;

    @Inject
    private CheckInOutDao checkInOutDao;

    @Inject
    private RoomDao roomDao;

    private ModelMapper mapper = new ModelMapper();

    @Override
    public boolean addReservation(Reservation reservation) {
        ReservationEntity reservationEntity = mapper.map(reservation, ReservationEntity.class);
        reservationEntity.setCheckInOut(
            CheckInOutEntity.builder()
                        .totalPrice(0d)
                        .build()
        );

        RoomEntity room = reservationEntity.getRoom();
        room.setAvailability(AvailabilityType.Booked);

        return dao.save(reservationEntity) && roomDao.update(room);

    }

    @Override
    public boolean updateReservation(Reservation oldReservation, Reservation newReservation) {
        ReservationEntity reservationEntity = mapper.map(newReservation, ReservationEntity.class);

        RoomEntity oldReservationRoom = mapper.map(oldReservation.getRoom(), RoomEntity.class);
        RoomEntity newReservationRoom = mapper.map(newReservation.getRoom(), RoomEntity.class);

        if (!oldReservationRoom.equals(newReservationRoom)) {
            oldReservationRoom.setAvailability(AvailabilityType.Available);
            newReservationRoom.setAvailability(AvailabilityType.Booked);
        }


        return dao.update(reservationEntity) && roomDao.update(oldReservationRoom) && roomDao.update(newReservationRoom);
    }

    @Override
    public boolean updateReservation(Reservation reservation) {
        return dao.update(mapper.map(reservation, ReservationEntity.class)) && roomDao.update(
                mapper.map(reservation.getRoom(), RoomEntity.class)
        );
    }

    @Override
    public boolean deleteReservation(Reservation reservation) {
        ReservationEntity reservationEntity = mapper.map(reservation, ReservationEntity.class);
        RoomEntity room = reservationEntity.getRoom();

        room.setAvailability(AvailabilityType.Available);

        return dao.delete(reservationEntity) && roomDao.update(room);
    }

    @Override
    public boolean confirmReservation(Reservation reservation) {
        reservation.setStatus(ReservationStatusType.Confirmed);
        reservation.getRoom().setAvailability(AvailabilityType.Booked);


        return updateReservation(reservation);
    }

    @Override
    public boolean cancelReservation(Reservation reservation) {
        reservation.setStatus(ReservationStatusType.Cancelled);
        reservation.getRoom().setAvailability(AvailabilityType.Available);

        return updateReservation(reservation);
    }

    @Override
    public boolean markReservationAsPending(Reservation reservation) {
        reservation.setStatus(ReservationStatusType.Pending);
        reservation.getRoom().setAvailability(AvailabilityType.Booked);


        return updateReservation(reservation);
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservationList = new ArrayList<>();

        dao.getAll().forEach(
                reservationEntity -> reservationList.add(
                    mapper.map(reservationEntity, Reservation.class)
                )
        );

        return reservationList;
    }
}
