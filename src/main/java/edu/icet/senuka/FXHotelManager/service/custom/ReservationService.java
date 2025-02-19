package edu.icet.senuka.FXHotelManager.service.custom;

import edu.icet.senuka.FXHotelManager.dto.Reservation;
import edu.icet.senuka.FXHotelManager.service.SuperService;

import java.util.List;

public interface ReservationService extends SuperService {
    public boolean addReservation(Reservation reservation);
    public boolean updateReservation(Reservation oldReservation, Reservation newReservation);
    public boolean updateReservation(Reservation reservation);
    public boolean deleteReservation(Reservation reservation);
    public boolean confirmReservation(Reservation reservation);
    public boolean cancelReservation(Reservation reservation);
    public boolean markReservationAsPending(Reservation reservation);
    public List<Reservation> getAll();
}
