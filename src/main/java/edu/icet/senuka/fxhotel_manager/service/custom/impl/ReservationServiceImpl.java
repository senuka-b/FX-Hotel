package edu.icet.senuka.fxhotel_manager.service.custom.impl;

import com.google.inject.Inject;
import edu.icet.senuka.fxhotel_manager.dto.Reservation;
import edu.icet.senuka.fxhotel_manager.dto.ReservationChartData;
import edu.icet.senuka.fxhotel_manager.entity.CheckInOutEntity;
import edu.icet.senuka.fxhotel_manager.entity.ReservationEntity;
import edu.icet.senuka.fxhotel_manager.entity.RoomEntity;
import edu.icet.senuka.fxhotel_manager.repository.custom.CheckInOutDao;
import edu.icet.senuka.fxhotel_manager.repository.custom.ReservationDao;
import edu.icet.senuka.fxhotel_manager.repository.custom.RoomDao;
import edu.icet.senuka.fxhotel_manager.service.custom.ReservationService;
import edu.icet.senuka.fxhotel_manager.util.types.AvailabilityType;
import edu.icet.senuka.fxhotel_manager.util.types.ReservationStatusType;
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

    @Override
    public List<Reservation> getCheckOutReservations() {
        List<Reservation> reservationList = new ArrayList<>();

        dao.getCheckOutReservations().forEach(reservationEntity -> reservationList.add(
                mapper.map(reservationEntity, Reservation.class)
        ));

        return reservationList;
    }

    @Override
    public List<ReservationChartData> getReservationChartData() {
        return dao.getReservationChartData();
    }
}
