package edu.icet.senuka.FXHotelManager.repository.custom;

import edu.icet.senuka.FXHotelManager.dto.ReservationChartData;
import edu.icet.senuka.FXHotelManager.entity.ReservationEntity;
import edu.icet.senuka.FXHotelManager.repository.CrudRepository;

import java.util.List;

public interface ReservationDao extends CrudRepository<ReservationEntity, Long> {
    public List<ReservationEntity> getCheckOutReservations();
    public List<ReservationChartData> getReservationChartData();
}
